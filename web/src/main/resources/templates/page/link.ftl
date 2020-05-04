<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>链接分享</title>
  <link rel="stylesheet"
        href="https://pic.zhoutao123.com/lib/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <#include "base/key.ftl">

</head>
<body>
<#include "base/header.ftl">

<div id="div">
  <div id="linkDiv">
      <#assign map=linkMap/>
      <#assign  keys=map?keys/>
      <#list keys as key>
        <h1 class="title">${key}</h1>
        <div class="detailDiv">
            <#list map[key] as link>
              <div class="card">
                <header class="card-header">
                  <p class="card-header-title">
                  <span class="icon has-text-info">
                    <i class="fa fa-envira"></i>
                  </span>
                      ${link.title}
                  </p>
                </header>
                <div class="card-content">
                  <div class="content">
                      ${link.content}
                  </div>
                </div>
                <footer class="card-footer">
                  <a href="#" class="card-footer-item">访问</a>
                  <a href="#" class="card-footer-item">分享</a>
                </footer>
              </div>
            </#list>
        </div>
      </#list>
  </div>
</div>


<style>

  <#--  链接xiangqingDIV-->

  .detailDiv {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    height: fit-content;
    padding-top: 30px;
    padding-bottom: 40px;
    border-radius: 10px;
    justify-content: space-around;
    box-shadow: 1px 1px 10px 1px #515a6e;
  }


  .title {
    margin-top: 40px;
  }

  #div {
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    align-items: center;
  }


  @media screen and (max-width: 1200px) {
    /*  卡片样式 */
    .card {
      width: 250px;
      margin-top: 20px;
      margin-bottom: 20px;
    }

    #linkDiv {
      display: flex;
      display: -webkit-flex;
      width: 95%;
      margin-top: 40px;
      min-height: 1024px;
      flex-direction: column;
      align-items: center;
    }

  }

  @media screen and (min-width: 1200px) {

    /*  卡片样式 */
    .card {
      width: 300px;
      margin: 20px;


    }

    #linkDiv {
      display: flex;
      display: -webkit-flex;
      width: 80%;
      margin-top: 40px;
      min-height: 1024px;
      flex-direction: column;
      align-items: center;
    }
  }


</style>
</body>
</html>
