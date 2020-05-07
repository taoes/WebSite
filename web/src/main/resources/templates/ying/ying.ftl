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

<div id="body">

  <div id="pictureContentDiv">

    <div id="menu">
      <select id="list">
        <option>默认</option>
          <#list 2017..2021 as type>
            <option class="dropdown-item" name="${type}">
                ${type}年
            </option>
          </#list>
      </select>
    </div>

    <div id="content">

    </div>
  </div>
</div>
<style>
  #body {
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    align-items: center;
  }

  #pictureContentDiv {
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    width: 90%;
    background-color: #FFFFFF;
    box-shadow: 1px 1px 10px 1px #515a6e;
  }


</style>


</body>

</html>
