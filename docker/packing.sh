echo "Start build....."
./gradlew  build -x test
echo "Build Complete!"
cp ./web/build/libs/web-1.0-SNAPSHOT.jar ./docker/blog.jar
docker build ./docker -t registry.cn-shanghai.aliyuncs.com/default-tao/blog:20200706
docker push registry.cn-shanghai.aliyuncs.com/default-tao/blog:20200706
