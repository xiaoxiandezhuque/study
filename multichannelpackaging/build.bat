echo  "$$package_begin$$"
sleep 1

gradle assembleRelease


echo -e "$$package success$$"
#桌面右上角弹出通知
notify-send build.sh "package down!"