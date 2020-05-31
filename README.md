# lego-rcx-lejos
Running the leJOS VM on a Lego Mindstorms RCX in 2020 takes some effort. This has been done and has been documented a few years ago: 
* 2015: https://www.instructables.com/id/Making-Mindstorms-RCX-Work-Again/
* 2017: http://www.johnholbrook.us/edu/RCX_guide.html

Many resources are available on github: https://github.com/BrickBot

## Hardware
Recently I got my hands on a Lego Robotics Invention System 1.5 from 1999. It contains an RCX 1.0 without a power jack, two touch sensors, a light sensor, two 9v motors and a serial IR Transmitter Tower. I got the Serial IR Tower working using a LogiLink AU0002E RS232 adapter.
The USB IR Tower has been reported not to work on 64-bit systems (because there was no driver). However on http://www.legoengineering.com/rcx-usb-tower-support/ there are instructions using the driver from https://www.ni.com/nl-nl/support/downloads/drivers/download.ni-visa.html, which was updated as recent as may 2020.
Extensive documentation of technical details of the RCX hardware is still available: http://www.mralligator.com/rcx/

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

## Jikes
To get rid of the Java 5 dependency, I switched to using Jikes for the classes that run on the RCX. Though the Jikes compiler has been unsupported for a long time, it works well for Java 1.1 which we need for leJOS. Classes that run on a PC can be compiled with a modern Java compiler. I use OpenJDK14 (OpenJ9).

## Scratch
To allow easier access to programming the RCX for children, I developed a minimal Scratch/SB3-to-leJOS/RCX compiler:
* https://github.com/swolla/scratch-lejos-rcx

## Next steps
As support for Java 5 is fading away quickly, the leJOS approach may not last long. The most recent spin-off is:
* HaikuVM http://haiku-vm.sourceforge.net/ (not updated since April 30, 2017)

Though over 3 years old now, it is still very useful. This virtual machine was originally created to support Java on Arduino and added RCX support in december 2015. At that time Java 7 had been around for a while and consequently HaikuVM requires Java 6 as a minimum. This allows for better support at least until october 2024 (when regular  support for Java SE 11 (LTS) will end).
