/*
 * (c) Copyright IBM Corporation 2007
 * $Id: addr_c.c,v 1.1 2007-06-25 16:41:32 srkodali Exp $
 * This file is part of X10 Runtime System.
 */

/** Example Program to illustrate the use of various X10Lib's
 ** Addressing routines, namely x10_reg_handler_addr,
 ** x10_get_handler_addr, and x10_make_gas_ref.
 ** For a set of n places 0, 1, ...., n-1, where n is an even
 ** number, each of the above routines is called, and the
 ** resulting data is printed.
 **/

#include <x10/x10lib.h>
#include <stdio.h>
#include <stdlib.h>

/* macro for checking X10Lib's return code */
#define XRC(statement) \
do { \
	x10_err_t xrc; \
	char err_msg_buf[X10_MAX_ERR_STRING]; \
	xrc = statement; \
	if (xrc != X10_OK) { \
		(void)x10_err_msg(xrc, err_msg_buf, X10_MAX_ERR_STRING); \
		fprintf(stderr, "[%s]: %s\n", argv[0], err_msg_buf); \
		x10_cleanup(); \
		exit(1); \
	} \
} while (0)

/* local pointer whose addresses we will exchange */
void *local_addr0;
void *local_addr1;

/* list of addresses gathered from all places */
void **addr_list;

int
main(int argc, char *argv[])
{
	int my_place; /* Our place ID */
	int num_places; /* Total number of places */
	int i;
	int ah; /* address handle */
	void *haddr; /* handler address */

	/* Initialize X10Lib */
	XRC(x10_init(NULL, 0));

	/* Get our place ID */
	XRC(x10_getenv(PLACE_ID, &my_place));

	/* Get the total number of places in the job */
	XRC(x10_getenv(NUM_PLACES, &num_places));

	/* This examples only supports even number of places */
	if ((num_places < 2) || ((num_places % 2) != 0)) {
		fprintf(stderr, "[%s]: this example requires an even"
			" number of places, but has been invoked with %d\n",
			argv[0], num_places);
		XRC(x10_finalize());
		exit(1);
	}

	/* allocate memory for the data addrs */
	addr_list = (void **)malloc(num_places * sizeof(void *));


	/* for each place, get the remote addr and store it */
	for (i = 0; i < num_places; i++) {
		x10_gas_ref_t ref;
		int place;
		void *addr;

		ref = x10_make_gas_ref(i, (void *)
				((i % 2 == 0) ? (&local_addr0) : (&local_addr1)));
		place = x10_gas2place(ref);
		addr = x10_gas2addr(ref);
		addr_list[place] = addr;
	}

	printf("place %d ==>\n", my_place);

	/* show local addrss */
	printf("\taddress of local_addr: 0x%llx\n",
			((my_place % 2 == 0) ? (&local_addr0) : (&local_addr1)));

	/* show list of remote addresses */
	for (i = 0; i < num_places; i++) {
		printf("\taddr_list[%d]: 0x%llx\n", i, addr_list[i]);
	}

	/* register handler address and retrieve it */
	ah = x10_reg_handler_addr(
			((my_place % 2 == 0) ? (&local_addr0) : (&local_addr1)));
	printf("[place %d] storing address 0x%x at position %d.\n",
			my_place,
			((my_place % 2 == 0) ? (&local_addr0) : (&local_addr1)),
			ah);

	XRC(x10_get_handler_addr(&haddr, ah));
	printf("[place %d] retrieved address 0x%x from postion %d.\n",
			my_place, haddr, ah);

	/* sync */
	x10_sync_global();

	/* free allocated memory */
	free((void *)addr_list);

	/* terminate the X10Lib context */
	XRC(x10_finalize());
	return 0;
}
