HEADERS = -I"${JAVA_HOME}\include" -I"${JAVA_HOME}\include\win32"

########

all: clean install

install:
	gcc -Wall -D_JNI_IMPLEMENTATION_ -Wl,--kill-at $(HEADERS) -shared -oWinFunctions.dll org_cryptomator_jni_WinDataProtection.cpp -lcrypt32

clean:
	IF EXIST "*.dll" DEL "*.dll"