<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title}</title>
  <link href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
        rel="stylesheet">
    <#include "base/key.ftl">
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
    <h1 class="title">
      <span class="icon has-text-info">
  <i class="fa fa-book"></i>
</span>
      读书笔记
    </h1>
    <div class="list noteList">
        <#list bookList as book>
          <div class="bookDiv" style="width: 200px">
            <img src="${book.coverImgUrl}" alt="${book.title}" class="cover"
                 onclick="openBookPage(${book.id})">
          </div>
        </#list>
    </div>
  </div>


  <div id="githubDiv">

    <H1 class="title">

<span class="icon has-text-info">
  <i class="fa fa-github"></i>
</span>
      我的Github
    </H1>

    <div class="list github">
        <#list githubList as github>
          <div class="bookDiv" style="width: 200px">
            <img src="${github.coverImgUrl}" alt="${github.title}" class="cover"
                 onclick="openGithubPage('${github.linkUrl}')">
          </div>
        </#list>
    </div>

  </div>

</div>
<#include "base/footer.ftl">

<style>


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
    display: flex;
    display: -webkit-flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }

  .noteList {
    width: 100%;
    min-height: 500px;
    height: fit-content;
    display: -webkit-flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-top: 30px;
    justify-content: center;
  }


  #githubDiv {
    width: 80%;
    height: fit-content;
    border-radius: 30px;
    margin-top: 30px;
    margin-bottom: 30px;
    display: flex;
    display: -webkit-flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }

  .github {
    width: 100%;
    height: fit-content;
    margin-top: 30px;
    display: flex;
    flex-direction: row;
    display: -webkit-flex;
    flex-wrap: wrap;
    justify-content: center;
  }

  .bookDiv {
    margin: 40px 15px;
  }

  @media screen and (min-width: 1200px) {

    #indexImg {
      object-fit: cover;
      width: 100%;
      height: 300px
    }
  }

  @media screen and (max-width: 1200px) {

    #indexImg {
      object-fit: cover;
      width: 100%;
      height: 300px
    }
  }

  @media screen and (max-width: 900px) {

    #indexImg {
      object-fit: cover;
      object-position: right;
      width: 100%;
    }
  }


  .cover {
    box-shadow: 1px 1px 10px 1px #515a6e;
    width: 100%;
  }

  .has-text-info {
    color: #0088EE !important;
  }


</style>


</body>
<script>
  function openBookPage(bookId) {
    window.location.href = `${path}/page/book/` + bookId
  }

  function openGithubPage(githubUrl) {
    window.open(githubUrl, "_blank");
  }
</script>

</html>
