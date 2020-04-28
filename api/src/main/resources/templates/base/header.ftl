<style>

  body, html {
    height: auto;
  }


  #headerDiv {
    display: flex;
    display: -webkit-flex;
    width: 100%;
    height: fit-content;
    align-items: baseline;
    justify-content: center;
    justify-items: center;
    background-color: white;
    border: 1px lightgrey solid;
  }


  /*标题样式*/

  #title {
    margin-right: 40px;

  }

  #headerCategory {
    list-style-type: none;
    margin: 0;
    padding: 0;
    background-color: white;
  }

  #headerCategory li {
    float: left;
    font-size: 18px;
    font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;
    margin: 10px;
  }

  #headerCategory li:hover {
    background-color: rebeccapurple;
  }

</style>


<div id="headerDiv">
  <h1 id="title">${title}</h1>
  <ul id="headerCategory">
    <li><a href="/">首页</a></li>
    <li><a href="/page/blog">博客</a></li>
    <li><a href="/page/doc">文档</a></li>
    <li><a href="/page/question">问答</a></li>
    <li><a href="/page/example">活动</a></li>
    <li><a href="/page/example">招聘</a></li>
    <li><a href="/page/app">移动端访问</a></li>
  </ul>

</div>




