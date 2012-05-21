#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <climits>
#include <cfloat>

#include <sys/time.h>

#include <algorithm>

int round_up (int x, int n) { return (x-1) - ((x-1)%n) + n; }

template<class T> T *zmalloc(size_t sz) {
    T *ptr = (T*)malloc(sz*sizeof(T));
    ::memset(ptr, 0, sz);
    return ptr;
}

unsigned long long currentTimeMillis() {
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return (unsigned long long)(tv.tv_sec * 1000LL + tv.tv_usec / 1000);
}

void print_clusters (float *clusters, int num_clusters, int dims) {
        for (int d=0 ; d<dims ; ++d) {
                for (int k=0 ; k<num_clusters ; ++k) {
                        if (k>0)
                                printf(" ");
                        printf("%.4f", clusters[k*dims+d]);
                }
                printf("\n");
        }
}


int main (int argc, char **argv)
{
        if (argc!=5) {
                fprintf(stderr, "Usage: %s <num_points> <num_clusters> <dimensions> <iterations>\n", argv[0]);
                exit(EXIT_FAILURE);
        }
        int num_slice_points = strtol(argv[1], NULL, 10);
        int num_clusters = strtol(argv[2], NULL, 10);
        int dim = strtol(argv[3], NULL, 10);
        int iterations = strtol(argv[4], NULL, 10);

        // clusters are dimension-major
        float *host_clusters     = zmalloc<float>(num_clusters*dim);
        float *old_clusters      = zmalloc<float>(num_clusters*dim);
        int *host_cluster_counts = zmalloc<int>(num_clusters);
            
        int num_slice_points_stride = num_slice_points;
            
        // these are pretty big so allocate up front
        float *host_points = zmalloc<float>(num_slice_points_stride*dim);
        srandom(0);
        for (int p=0 ; p<num_slice_points*dim ; ++p) {
                host_points[p] = random()/(RAND_MAX+1.0);
        }
        for (int p=0 ; p<num_clusters ; ++p) {
                for (int d=0 ; d<dim ; ++d) {
                        host_clusters[p*dim+d] = host_points[p+d*num_slice_points_stride];
                        //host_clusters[p*dim+d] = host_points[p*dim+d];
                }
        }
            
        unsigned long long k_start_time = currentTimeMillis();
        for (int i=0 ; i<iterations ; ++i) {

                std::swap(host_clusters, old_clusters);
                ::memset(host_clusters, 0, num_clusters*dim*sizeof(*host_clusters));
                ::memset(host_cluster_counts, 0, num_clusters*sizeof(*host_cluster_counts));

                #define UR 8
                for (int p=0 ; p<num_slice_points ; p+=UR) {
                        int closest[UR];
                        for (int i=0 ; i<UR ; ++i) closest[i] = -1;
                        float closest_dist[UR];
                        for (int i=0 ; i<UR ; ++i) closest_dist[i] = FLT_MAX;
                        for (int k=0 ; k<num_clusters ; ++k) {
                                float dist[UR];
                                for (int i=0 ; i<UR ; ++i) dist[i] = 0;
                                for (int d=0 ; d<dim ; ++d) {
                                        float tmp[UR];
                                        for (int i=0 ; i<UR ; ++i) tmp[i] = host_points[(p+i)+d*num_slice_points_stride] - old_clusters[k*dim+d];
                                        //for (int i=0 ; i<UR ; ++i) tmp[i] = host_points[(p+i)*dim+d] - old_clusters[k*dim+d];
                                        for (int i=0 ; i<UR ; ++i) dist[i] += tmp[i] * tmp[i];
                                }
                                for (int i=0 ; i<UR ; ++i) if (dist[i] < closest_dist[i]) {
                                        closest_dist[i] = dist[i];
                                        closest[i] = k;
                                }
                        }
                        for (int d=0 ; d<dim ; ++d) {
                                for (int i=0 ; i<UR ; ++i) host_clusters[closest[i]*dim+d] += host_points[(p+i)+d*num_slice_points_stride];
                                //for (int i=0 ; i<UR ; ++i) host_clusters[closest[i]*dim+d] += host_points[(p+i)*dim+d];
                        }
                        for (int i=0 ; i<UR ; ++i) host_cluster_counts[closest[i]]++;
                }

                for (int k=0 ; k<num_clusters ; ++k) {
                        for (int d=0 ; d<dim ; ++d) host_clusters[k*dim+d] /= host_cluster_counts[k];
                }

        }
        unsigned long long k_stop_time = currentTimeMillis();

        print_clusters(host_clusters, num_clusters, dim);

        printf("time taken: %f seconds\n", (k_stop_time - k_start_time)/1E3);

            
        return EXIT_SUCCESS;
}

// vim: shiftwidth=8:tabstop=8:expandtab

