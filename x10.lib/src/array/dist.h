#ifndef __X10_DIST_H__
#define __X10_DIST_H__

#include "types.h"
#include "region.h"

namespace x10lib{

  /**
   * Distribution class (ABSTRACT)
   *
   */
  template <int RANK>
  class Dist
  {

  public:

     static Dist<RANK>* makeUnique () {

       return new UniqueDist<1>();
     }
  
  public:
 
    Dist () {} 
    /** The index region for places and region should be the same. For each point in this
     * region, region(p) is mapped to places(p). Note: An array of places corresponds to
     * ARMCI's notion of a domain.
     */

    Dist (const Region<RANK>* region) :
      region_ (region)
    {
      places_ = new place_t [region->card()];
      for (place_t i = 0; i < numPlaces(); i++)
        places_[i] = i;
    }
  
    Dist (const Region<RANK>* region, place_t* places) :
      region_ (region)
    {
      places_ = new place_t [region->card()];
      memcpy (places_, places, sizeof(place_t) * region->card());
    }
 
    Dist (const Dist<RANK>& other) :
      region_ (other.region_),
    {
      places_ = new place_t [region->card()];
      memcpy (places_, other.places, sizeof(place_t) * region->card());
    }

    virtual Dist<RANK>* clone() const = 0;
 
    ~Dist () 
    {
      delete [] places_;
    }
    
    virtual const place_t place (const Point<RANK>& p) const = 0;

    const Region<RANK>* region () const{

      return region_;
    }

    const int* places() const{

      return  places_;

    }
    
  protected:
    
    const Region<RANK>* region_;
    place_t* places_;
  
  };

  /** A constant distribution maps every point in its underlying region to the same place.
   */ 
  template <int RANK>
  class ConstDist : public Dist<RANK>
  {
  public:
 
    ConstDist (const Region<RANK>* region, const place_t p) :
      Dist<RANK> (region, new place_t (p)) {}

    ConstDist (const ConstDist<RANK>& other) :
      Dist<RANK> (other.region_, other.places_) 
    {

    } 

    virtual ConstDist<RANK>* clone() const 
    {
      return new ConstDist<RANK> (*this);
    }
    const place_t place (const Point<RANK>& p) const
    { 
      return this->places_[0];
    }

  };


  template <int RANK>
  class UniqueDist : public Dist<RANK>
  {

  public:

    UniqueDist () :
      Dist(new RectangularRegion<RANK>(Point<RANK>(numPlaces()-1)))
      {
      } 
 
    UniqueDist (const Region<RANK>* region, place_t* places) :
      Dist<RANK> (region, places)
    {
       
    }

    UniqueDist (const UniqueDist<RANK>& other) :
      Dist<RANK> (other.region_, other.places_) 
    {

    } 

    virtual UniqueDist<RANK>* clone() const 
    {
      return new UniqueDist<RANK> (*this);
    }
  
    const place_t place (const Point<RANK>& p) const 
    {
      return this->places_[this->region_->ord (p)];
    }
  };
  
}
#endif /*X10DIST_H_*/

// Local Variables:
// mode: C++
// End:
