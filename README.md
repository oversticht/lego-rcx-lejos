# lego-rcx-lejos
Running the leJOS VM on a Lego Mindstorms RCX in 2020 takes some effort. This has been done and documented a few years ago: 
* 2015: https://www.instructables.com/id/Making-Mindstorms-RCX-Work-Again/
* 2017: http://www.johnholbrook.us/edu/RCX_guide.html

## Hardware
Recently I got my hands on a Lego Robotics Invention System 1.5 from 1999. It contains an RCX 1.0 without a power jack, two touch sensors, a light sensor, two 9v motors and a serial IR Transmitter Tower. The USB IR Tower is reported not to work on 64-bit systems (no driver). I got the Serial IR Tower working using a LogiLink AU0002E RS232 adapter.
And extensive technical details about the RCX hardware are still available: http://www.mralligator.com/rcx/

### Infrared
Some infrared signals for the RCX are documented here: https://github.com/JorgePe/UniBrick and here: https://ofalcao.pt/blog/2017/decoding-rcx-ir-command-protocol

## Original software
The original software (available here: https://philohome.com/sdk25/sdk25.htm) was documented to be designed for Windows(c) 95/98 and doesn't run on my 64-bit Windows 10 computer.

## Alternative software
Then I looked for working alternatives to the Lego SDK. A few still available resources are:
* RobotC 2.03 (2012): http://www.robotc.net/download/rcx/
* BricxCC and NQC: http://bricxcc.sourceforge.net/ and http://bricxcc.sourceforge.net/nqc
* BrickOS: http://brickos.sourceforge.net/
* Quite C: http://www.elenafrancesco.org/old/lego/qc/index.html
* https://pbrick.info/rcx-firmware/
* leJOS: http://www.lejos.org/rcx.php

## leJOS
I choose leJOS because I know the Java programming language. As the most recent version seems to be the best option to try, I first attempted to get leJOS RCX 3.0 RC2 working but that version turns out to require the USB IR Tower so I switched to leJOS 2.1.0.
This version requires working with Java 1.1. Support for Java 1.1 was dropped in the Java Development Kit 1.6 so I got Java SE 5 (JDK) from https://www.oracle.com/java/technologies/java-archive-javase5-downloads.html. Even with this JDK, source code written for Java 4 or Java 5 can't be compiled to Java 1.1 binary class files, so the parameter -source 1.3 must be specified explicitly.

## Programming environment
As leJOS is Java, I have IntelliJ IDEA community edition as programming environment. This doesn't work with Java 5 and older versions. IntelliJ IDEA does however still support Apache Ant 1.9.14 which in turn support Java 5 and newer.

Java 5 is the maximum version for leJOS and the minimum version voor Apache Ant 1.9.14 so Java 5 is just right. An Ant build script can compile the java source code and send it directly to the RCX, all from the programming environment.
