<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title}</title>
  <script src="https://cdn.bootcdn.net/ajax/libs/marked/1.0.0/marked.min.js"></script>
  <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>

     <#include "base/key.ftl">

  <style>

  </style>
</head>
<body>
<#include "./base/header.ftl">


<div class="contentDiv">
  <h1 style="margin-top: 20px" class="title is-2">${title}</h1>
  <div class="detailDiv">
    <div id="content">
        ${content}
    </div>
    <div id="side">
      <div class="card" style="width: 100%;height: fit-content">
        <header class="card-header">
          <p class="card-header-title">
            热门推荐
          </p>
        </header>

        <div class="card-content">
          <div class="content">
              <#list bookList as book>
                <p onclick="openNewBookPage(${book.id})" style="cursor: pointer">${book.title}</p>
              </#list>
          </div>
        </div>

      </div>
    </div>
  </div>

</div>
<#include "base/footer.ftl">


</body>
<style>
  .contentDiv {
    width: 100%;
    min-height: 90%;
    background-color: #EFEFEF;
    padding-bottom: 20px;
    height: fit-content;
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    align-items: center;
  }

  #content {
    width: 80%;
    margin-top: 20px;
    padding: 20px;
    flex-grow: 1;
    border-radius: 10px;
    box-shadow: 1px 1px 10px 1px lightgrey;
    background-color: #FFFFFF;
  }


  .title {
    font-weight: 900;
    margin-top: 20px;
    font-family: Serif, serif;
  }

  .detailDiv {
    display: flex;
    display: -webkit-flex;
    width: 80%;
    min-height: 300px;
  }

  @media screen and (max-width: 800px) {

    #side {
      display: none;
    }

    .detailDiv {
      display: flex;
      display: -webkit-flex;
      width: 100%;
      min-height: 300px;
    }

  }

  @media screen  and (max-width: 1200px) {
    #side {
      display: none;
    }

    .detailDiv {
      display: flex;
      display: -webkit-flex;
      width: 100%;
      min-height: 300px;
    }
  }

  @media screen  and (min-width: 1200px) {
    #side {
      width: 300px;
      height: 100px;
      display: flex;
      display: -webkit-flex;
      flex-grow: 0;
      margin: 20px 20px 20px 30px;
      background-color: #FFFFFF;
      border-radius: 10px;
      box-shadow: 1px 1px 10px 1px lightgrey;
    }

    .detailDiv {
      display: flex;
      display: -webkit-flex;
      width: 80%;
      min-height: 300px;
    }
  }

</style>
<script>
  function toBookIndexPage() {

  }

  function toIndexPage() {
    window.location.href = 'http://www.zhoutao123.com';
  }

  /**
   * 跳转到新的图书页面
   * @param bookId
   */
  function openNewBookPage(bookId) {
    window.open("/page/book/" + bookId)
  }
</script>

</html>
