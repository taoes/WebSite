<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>资源不存在</title>
    <#include "base/key.ftl">
</head>
<body>
<#include "base/header.ftl">

<div id="errorDiv">
  <p class="errorCode">${code}</p>
  <p style="margin-bottom: 20px">${msg!}</p>
  <button class="button is-link" onclick="window.location.href='/'">返回首页</button>
</div>
</body>
<style>
  #errorDiv{
    margin-top: 40px;
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .errorCode {
    font-size: 300px;
    color: #0088EE;
    font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;
  }
</style>
</html>
