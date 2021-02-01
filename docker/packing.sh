echo "开始构建 MFour....."
./gradlew  build -x test

if [[ $? -ne 0 ]]; then
  echo "编译失败....."
  exit -1
fi


echo "编译完成,正在准备打包...."
rm ./docker/blog.jar
cp ./web/build/libs/web-1.0-SNAPSHOT.jar ./docker/blog.jar
docker build ./docker -t registry.cn-hangzhou.aliyuncs.com/seven-tao/blog:20210201-2
docker push registry.cn-hangzhou.aliyuncs.com/seven-tao/blog:20210201-2
echo "推送镜像完成....."