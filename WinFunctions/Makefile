HEADERS = -I"C:\Program Files\Java\jdk1.8.0_102\include" -I"C:\Program Files\Java\jdk1.8.0_102\include\win32"

########

all: clean install

install:
	gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at $(HEADERS) -shared -oWinFunctions.dll org_cryptomator_jni_WinDataProtection.cpp -lcrypt32

clean:
	rm *.dll