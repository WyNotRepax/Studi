# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.10

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion"

# Include any dependencies generated for this target.
include CMakeFiles/CLion.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/CLion.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/CLion.dir/flags.make

CMakeFiles/CLion.dir/main.cpp.o: CMakeFiles/CLion.dir/flags.make
CMakeFiles/CLion.dir/main.cpp.o: main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/CLion.dir/main.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CLion.dir/main.cpp.o -c "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/main.cpp"

CMakeFiles/CLion.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CLion.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/main.cpp" > CMakeFiles/CLion.dir/main.cpp.i

CMakeFiles/CLion.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CLion.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/main.cpp" -o CMakeFiles/CLion.dir/main.cpp.s

CMakeFiles/CLion.dir/main.cpp.o.requires:

.PHONY : CMakeFiles/CLion.dir/main.cpp.o.requires

CMakeFiles/CLion.dir/main.cpp.o.provides: CMakeFiles/CLion.dir/main.cpp.o.requires
	$(MAKE) -f CMakeFiles/CLion.dir/build.make CMakeFiles/CLion.dir/main.cpp.o.provides.build
.PHONY : CMakeFiles/CLion.dir/main.cpp.o.provides

CMakeFiles/CLion.dir/main.cpp.o.provides.build: CMakeFiles/CLion.dir/main.cpp.o


CMakeFiles/CLion.dir/lib/web_request.c.o: CMakeFiles/CLion.dir/flags.make
CMakeFiles/CLion.dir/lib/web_request.c.o: lib/web_request.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/CLion.dir/lib/web_request.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/CLion.dir/lib/web_request.c.o   -c "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/lib/web_request.c"

CMakeFiles/CLion.dir/lib/web_request.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/CLion.dir/lib/web_request.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/lib/web_request.c" > CMakeFiles/CLion.dir/lib/web_request.c.i

CMakeFiles/CLion.dir/lib/web_request.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/CLion.dir/lib/web_request.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/lib/web_request.c" -o CMakeFiles/CLion.dir/lib/web_request.c.s

CMakeFiles/CLion.dir/lib/web_request.c.o.requires:

.PHONY : CMakeFiles/CLion.dir/lib/web_request.c.o.requires

CMakeFiles/CLion.dir/lib/web_request.c.o.provides: CMakeFiles/CLion.dir/lib/web_request.c.o.requires
	$(MAKE) -f CMakeFiles/CLion.dir/build.make CMakeFiles/CLion.dir/lib/web_request.c.o.provides.build
.PHONY : CMakeFiles/CLion.dir/lib/web_request.c.o.provides

CMakeFiles/CLion.dir/lib/web_request.c.o.provides.build: CMakeFiles/CLion.dir/lib/web_request.c.o


# Object files for target CLion
CLion_OBJECTS = \
"CMakeFiles/CLion.dir/main.cpp.o" \
"CMakeFiles/CLion.dir/lib/web_request.c.o"

# External object files for target CLion
CLion_EXTERNAL_OBJECTS =

CLion: CMakeFiles/CLion.dir/main.cpp.o
CLion: CMakeFiles/CLion.dir/lib/web_request.c.o
CLion: CMakeFiles/CLion.dir/build.make
CLion: CMakeFiles/CLion.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable CLion"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/CLion.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/CLion.dir/build: CLion

.PHONY : CMakeFiles/CLion.dir/build

CMakeFiles/CLion.dir/requires: CMakeFiles/CLion.dir/main.cpp.o.requires
CMakeFiles/CLion.dir/requires: CMakeFiles/CLion.dir/lib/web_request.c.o.requires

.PHONY : CMakeFiles/CLion.dir/requires

CMakeFiles/CLion.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/CLion.dir/cmake_clean.cmake
.PHONY : CMakeFiles/CLion.dir/clean

CMakeFiles/CLion.dir/depend:
	cd "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion" "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion" "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion" "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion" "/mnt/z/Studi/Betriebssysteme/Blatt 6/CLion/CMakeFiles/CLion.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/CLion.dir/depend

