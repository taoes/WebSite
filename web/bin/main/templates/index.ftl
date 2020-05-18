<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title}</title>
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
        <i class="fa fa-envira"></i>
    </span>
      读书笔记
    </h1>
    <div class="list noteList">
        <#list bookList as book>
          <div class="bookDiv">
            <a href="/page/book/${book.id}" style="display: block">
              <img src="${book.coverImgUrl}" alt="${book.title}" class="cover">
            </a>
          </div>
        </#list>
    </div>
  </div>


  <div id="githubDiv">

    <H1 class="title">

      <span class="icon has-text-info">
        <i class="fa fa-envira"></i>
      </span>
      我的Github
    </H1>

    <div class="list github">
        <#list githubList as github>
          <div class="bookDiv">
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

  .title {
    font-family: Serif, serif;
  }

  @media screen and (min-width: 1200px) {

    #indexImg {
      object-fit: cover;
      width: 100%;
      height: 300px
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

    .bookDiv {
      margin: 40px 40px;
      width: 200px;
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
  }

  @media screen and (max-width: 1200px) {

    #indexImg {
      object-fit: cover;
      object-position: left;
      width: 100%;
      height: 300px
    }


    #noteListDiv {
      width: 95%;
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

    .bookDiv {
      margin: 30px 22px;
      width: 150px;
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
      width: 95%;
      height: fit-content;
      border-radius: 30px;
      margin-top: 30px;
      margin-bottom: 30px;
      display: flex;
      display: -webkit-flex;
      justify-content: space-around;
      align-items: center;
      flex-direction: column;
    }

    .github {
      width: 100%;
      height: fit-content;
      margin: 30px 22px;
      display: flex;
      flex-direction: row;
      display: -webkit-flex;
      flex-wrap: wrap;
      justify-content: center;
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
