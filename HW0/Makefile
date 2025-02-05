# file: Makefile
#
#	You must NOT change this Makefile. 
#		This Makefile includes another file "make-include".
#		I provide a sample "make-include" to show 
#		how I customize my configuration, namely,
#			(Cygwin installed on a Windows 10 laptop).
#		You should customize it for own environment
#			(Mac, Linux, Windows).
#		If you are in Windows, then I suggest you install the Cygwin
#		to give you a Unix/Linux like operating system inside Windows.
#		You can freely move between Windows and Cygwin, and share
#		each other's files.
# 
#	HINTS:
#		-- This file is best viewed with an editor
#			that understands Makefile syntax and provides syntax colors
#		-- Note the following "standard" variables:
#
#				p: program to run/compile
#				ss: seed for random generator (ss=0 is special)
#				nn: int value used by many programs
#				mm: int value if we need a second int 
#
#############################################
#	Prof. Chee Yap (Basic Algorithms)
#############################################

# ================================================
# ENVIRONMENT CUSTOMIZATION:
# ================================================
# Put your customizations in make-include file.
# But to ensure that the default target is NOT over-ridden
#	by make-include, we put the default here:
default: ca

-include make-include

# program 
# ================================================
# Hello should be always available for small Java experiments
p=Hello
p=Hello1 		# think of Hello1 as a variant of Hello
p=BdayParadox

# standard command line arguments:
# ================================================
# the first 3 arguments for command lines are three integers:
ss=111
nn=10
mm=1
# Other arguments are optional:
a3=true
a4=abc
# We assemble them into a single argument:
args=$(ss) $(nn) $(mm) $(a3) $(a4) 

# ================================================
# TARGETS
# ================================================
default: ca

h help:
	-@echo "HELP:"
	-echo "    >make                  -- compile everything" 
	-@echo "    >make c               -- compile \$$(p)"
	-@echo "    >make r               -- run \$$(p)" 
	-@echo "    >make cr              -- compile and run \$$(p)" 
	-@echo "    >make t1 nn=12 ss=0   -- test1" 
	-@echo "    >make t2 mm=2 nn=321  -- test2 (etc)"

# default is to compile all *java programs
ca compileAll:
	test -d bin || mkdir bin
	$(javac) -d bin *.java 

# for doing only ONE program (great in debugging):
cr compileRun: c r

c compile javac compileOne:
	test -d bin || mkdir bin
	$(javac) -d bin $(p).java

r run java: 
	$(java) -classpath bin $(p) $(args)

r0 run0 java0: 
	$(java) -classpath bin $(p) 

# running a variant of $(p):
r1 run1 java1: 
	$(java) -classpath bin $(p)1 $(args) 

s showargs:
	@printf "ss= $(ss), nn=$(nn), mm=$(mm)\n"
	@printf "args= $(args)\n"

hello:
	@echo "Hello program is always available for testing!"
	$(java) -classpath bin Hello $(ss) $(nn)

t1 test1:
	@echo "test 1:"
	$(java) -classpath bin BdayParadox 111 23 0

t2 test2:
	@echo "test 2:"
	$(java) -classpath bin BdayParadox 1111 23 0

t3 test3:
	@echo "test 3:"
	$(java) -classpath bin BdayParadox 111 20 1

t4 test4:
	@echo "test 4:"
	$(java) -classpath bin BdayParadox 11 20 1

# ================================================
# HOUSEKEEPING
# ================================================
e edit g gvim:
	$(gvim) $(p).java &

m makefile:
	$(gvim) Makefile &

clean:
	-rm -f bin/* .*~ *.class *~ src/*~  src/.*

v vclean: clean
	-rm -r bin

# Create zip file "hwXXX-YYY.zip" (e.g., "hw0-yap.zip") one level up:
zip:	vclean
	-test -f ../hw$(hwNumber)-$(myName).zip && \
			rm ../hw$(hwNumber)-$(myName).zip
	zip ../hw$(hwNumber)-$(myName).zip \
	 ../hw$(hwNumber)-$(myName)/Hello.java \
	 ../hw$(hwNumber)-$(myName)/Hello1.java \
	 ../hw$(hwNumber)-$(myName)/README \
	 ../hw$(hwNumber)-$(myName)/Makefile \
	 ../hw$(hwNumber)-$(myName)/make-include \
	 ../hw$(hwNumber)-$(myName)/TestOutput \
	 ../hw$(hwNumber)-$(myName)/HINTS \
	 ../hw$(hwNumber)-$(myName)/JavaNotes.pdf \
	 ../hw$(hwNumber)-$(myName)/hw0-yap.pdf \

# ================================================
# END
# ================================================
