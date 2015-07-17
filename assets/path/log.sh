#/bin/bash #导出腾讯视频所有日志

clear

date=$(date +%Y-%m-%d-%H%M%S)
path=/Users/zhongzhicong/Documents/10_百度云同步盘/百度云同步盘/工作云同步/01_workpath/LOG/
log=log_

adb pull /data/anr $path$log$date 
adb pull /sdcard/Android/data/com.tencent.qqlive/files/log $path$log$date 
adb pull /sdcard/.QQLive/*. $path$log$date 
adb shell dumpsys cpuinfo >$path$log$date/cpuinfo.txt
adb shell dumpsys meminfo >$path$log$date/meminfo.txt 
adb shell dumpsys activity >$path$log$date/activity.txt 
adb logcat -v time -d > $path$log$date/logcat.txt

echo finish log 
#end
