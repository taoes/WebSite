echo "开始编译,请等待..."
RESULT=$(./gradlew build)
if [[ $? -eq 0 ]]; then
    echo "编译完成，正在上传到服务器 /tmp 目录..."
    scp ./web/build/libs/web-1.0-SNAPSHOT.jar root@api.zhoutao123.com:/tmp/web.jar
    echo "文件上传完成"
else
    echo  "编译失败"
    echo "$RESULT"
fi













