<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>

  </style>
</head>
<body>
<#include "./base/header.ftl">
<div>
  <img src="${config["INDEX_IMG"]}" alt="" id="indexImg">
</div>

<div id="share">

  <div id="noteListDiv">
    <button class="button is-link">
      读书笔记
    </button>
    <div class="list noteList">
        <#list bookList as book>
          <div class="bookDiv" style="width: 300px">
            <img src="${book.coverImgUrl}" alt="${book.title}" class="cover">
          </div>
        </#list>

    </div>
  </div>


  <div id="githubDiv">

    <button class="button is-link">
      我的Github
    </button>

    <div class="list github">
        <#list githubList as github>
          <div class="bookDiv" style="width: 300px">
            <img src="${github.coverImgUrl}" alt="${github.title}" class="cover">
          </div>
        </#list>
    </div>

  </div>

</div>

<style>
  <#--  首页图片-->
  #indexImg {
    object-fit: cover;
    width: 100%;
    height: 600px
  }


  /*  数据列表*/
  #share {
    display: flex;
    display: -webkit-flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  #noteListDiv {
    width: 80%;
    height: fit-content;
    border-radius: 30px;
    margin-top: 30px;
    margin-bottom: 30px;

  }

  .noteList {
    width: 100%;
    min-height: 500px;
    height: fit-content;
    display: -webkit-flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-top: 30px;
    justify-content: space-around;
  }


  #githubDiv {
    width: 80%;
    height: fit-content;
    border-radius: 30px;
    margin-top: 30px;
    margin-bottom: 30px;
  }

  .github {
    width: 100%;
    height: 500px;
    margin-top: 30px;
    display: flex;
    flex-direction: row;
    display: -webkit-flex;
    flex-wrap: wrap;
    justify-content: space-around;
  }

  .bookDiv {
    margin-left: 15px;
    margin-right: 15px;
    margin-top: 20px;
  }

  .cover {
    box-shadow: 1px 1px 10px 1px #515a6e;
    width: 300px;
  }
</style>

</body>
</html>
