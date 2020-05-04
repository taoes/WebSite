<div>
  <nav class="navbar is-dark" role="navigation" aria-label="main navigation">

    <div id="navbarBasicExample" class="navbar-menu">

      <a class="navbar-item" href="">
        <img src="/logo-white.png" width="112"
             height="28">
      </a>

      <div class="navbar-start">
        <a class="navbar-item" href="${path}/">
          主页
        </a>

        <a class="navbar-item" href="${path}/page/doc/123">
          文档
        </a>


        <a class="navbar-item" href="https://www.yuque.com/zhoutao123" target="_blank">
          语雀
        </a>

        <a class="navbar-item" href="${path}/page/blog">
          博客
        </a>

        <a class="navbar-item" href="${path}/page/code/123">
          代码
        </a>

        <a class="navbar-item" href="${path}/page/link.html">
          链接
        </a>


        <a class="navbar-item" href="${path}/page/about.html">
          关于
        </a>

        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link">
            工具
          </a>
          <div class="navbar-dropdown">
            <a class="navbar-item" href="${path}/page/tools/no.html">
              Json格式化
            </a>
            <a class="navbar-item" href="${path}/page/tools/no.html">
              进制转换
            </a>
            <a class="navbar-item" href="${path}/page/tools/no.html">
              时间戳
            </a>
            <a class="navbar-item" href="${path}/page/tools/no.html">
              其他资源
            </a>
          </div>
        </div>


          <#--      <div class="navbar-item has-dropdown is-hoverable">-->
          <#--        <a class="navbar-link">-->
          <#--          更多-->
          <#--        </a>-->

          <#--        <div class="navbar-dropdown">-->
          <#--          <hr class="navbar-divider">-->
          <#--          <a class="navbar-item" href="${path}/page/support/123">-->
          <#--            赞助-->
          <#--          </a>-->
          <#--        </div>-->
          <#--      </div>-->
      </div>

      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-primary">
              <strong>登录系统</strong>
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>


  <div style="height: 50px"></div>
</div>

<style>
  .navbar {
    position: fixed;
    width: 100%;
    z-index: 100;
  }
</style>
