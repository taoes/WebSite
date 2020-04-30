<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title}</title>
    <#include "base/key.ftl">

  <style>
    .iconfont {
      color: white;
      font-size: 28px;
      font-style: normal;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }

  </style>
</head>
<body>
<#include "base/header.ftl">

<div class="personInfo">
  <div class="bgContent">
    <img alt="头像加载失败" class="headImg" src="http://pic.zhoutao123.com/picture/header.jpeg">
    <h1 class="whiteSpan">燕归来兮 - 周涛</h1>
    <div id="link">
        <#list  personInfoList as info>
          <i class="${info.icon} iconfont"></i>
        </#list>


    </div>
    <span class="whiteSpan subTitle">
                Talk is cheap, show me your code
            </span>
  </div>

</div>

<style>


  .bgContent {
    display: flex;
    display: -webkit-flex;
    background: url('http://pic.zhoutao123.com/picture/background/bg-contact.jpg');
    flex-direction: column;
    min-height: 450px;
    align-items: center;
    justify-content: center;
  }


  .headImg {
    width: 200px;
    height: 200px;
    object-fit: cover;
    border-radius: 100px;
    margin-bottom: 30px;
    transition: all 3s;

  }

  .linkIcon:hover {
    transform: rotate(360deg);
  }


  .headImg:hover {
    transform: rotate(360deg);
  }


  #contentContainer {
    align-content: center;
    width: 90%;
    height: fit-content;
    margin-top: 30px;
    margin-bottom: 30px;
    border-radius: 10px;
    background-color: #FFF;
    padding: 10px;
    box-shadow: 5px 5px 30px 1px #515a6e;
  }

  .whiteSpan {
    color: white;
  }

  .title {
    letter-spacing: 10px;
    margin-top: 30px;
  }

  .subTitle {
    margin-top: 10px;
    text-transform: uppercase;
  }


  .linkIcon {
    margin-left: 10px;
  }
</style>

</body>
</html>
