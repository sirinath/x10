###################################################
###################################################
## Name:  	X10 application test
## Created by: 	Juemin Zhang
## Contact:   	zhangj@us.ibm.com
###################################################
###################################################

# Inputs 
# target      --- default source x10 file
# target_list --- list of targets, which are built independently
# build_path  --- the directory to store the compiling object files
# gml_path    --- GML library path


# runtime settings
# numplaces = 1
# test_args = 


###################################################
# Source and target file
###################################################

# test source
x10src		= $(target).x10

###################################################
# settings
###################################################

# lib source
gml_inc		= $(gml_path)/include
gml_lib		= $(gml_path)/lib
gml_scripts	= $(gml_path)/scripts
###################################################
# Build rules
###################################################
include $(gml_scripts)/system_setting.mk

#include $(gml_scripts)/build_managed.mk
#include $(gml_scripts)/build_native.mk
#include $(gml_scripts)/build_native_mpi.mk

#Build defautl target for all runtime backend and runtime transports
all	:
	$(foreach rt, $(runtime_list), $(MAKE) all_$(rt); )

###
clean	::
		rm -rf $(build_path)  $(target)_mpi $(target)_sock $(target)_lapi
		
cleanall:: 
		rm -rf $(build_path)
		$(foreach f, $(target_list), rm -f $(f)_mpi $(f)_sock $(f)_lapi; )

###-----------------------------------------
##  build library
lib	:
	cd $(gml_path) && make all -f Makefile all

check_gml		: 
	@(if test -d $(gml_inc); then echo "Find GML include path"; \
	else echo "Cannot find $(gml_inc). Apps compiling may fail. If so, rebuilt your GML library"; fi)

####----------------------------------------------
.PHONY		: lib all runall sock mpi java clean cleanall all_mpi all_sock all_lapi all_java chk_gml_inc help
####----------------------------------------------

help ::
	@echo "---------------------- build/test on all tests --------------------";
	@echo " make all       : build default test on all backends and runtime transports:$(runtime_list)";
	@echo " make clean     : clean up the build for default target - $(target)";
	@echo " make lib       : build gml library";
	@echo " make clean     : clean up default build";
	@echo " make cleanall : clean up alltests build";
	@echo "";

