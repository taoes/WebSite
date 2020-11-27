echo "Start build....."
./gradlew  build -x test

if [[ $? -ne 0 ]]; then
  echo "编译失败....."
fi


echo "Build Complete!"
rm ./docker/blog.jar
cp ./web/build/libs/web-1.0-SNAPSHOT.jar ./docker/blog.jar
docker build ./docker -t registry.cn-shanghai.aliyuncs.com/default-tao/blog:20201114
docker push registry.cn-shanghai.aliyuncs.com/default-tao/blog:20201114
