<div id="pcMenu">
  <nav class="navbar is-dark" role="navigation" aria-label="main navigation">
    <div id="navbarBasicExample" class="navbar-menu">
      <a class="navbar-item" href="/">
        <img src="/logo-white.png" width="112"
             height="28">
      </a>

      <div class="navbar-start">
        <a class="navbar-item" href="${path}/">
          主页
        </a>
        <a class="navbar-item hide" href="${path}/page/doc/123">
          文档
        </a>
        <a class="navbar-item" href="https://www.yuque.com/zhoutao123" target="_blank">
          语雀
        </a>
        <a class="navbar-item" href="${path}/page/blog">
          博客
        </a>
        <a class="navbar-item hide" href="${path}/page/code/123">
          代码
        </a>
        <a class="navbar-item hide" href="${path}/page/link.html">
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
      </div>

      <div class="navbar-item">
        <div class="buttons">
          <a class="button is-link is-small" href="https://github.com/taoes">
            <i class="fa fa-github-alt"></i>
            <strong>&nbsp;&nbsp;访问我的 Github</strong>
          </a>
        </div>
      </div>
    </div>

  </nav>
  <div style="height: 50px"></div>
</div>

<div id="mobileMenu">
  <div id="mobileMenuDiv">
    <a class="navbar-item" href="/">
      <img src="/logo-white.png" width="112"
           height="28">
    </a>

    <div style="display:flex; margin-left: auto;margin-right:20px;align-items: center">
      <button class="button is-link is-small" onclick="actionPupMenu()">
        <i class="fa fa-bars fa-lg"></i>
        <strong>&nbsp;菜单</strong>
      </button>
    </div>
  </div>
</div>

<div id="popMenu" style="display: none">
  <a class="navbar-item" href="${path}/">
    主页
  </a>
  <a class="navbar-item hide" href="${path}/page/doc/123">
    文档
  </a>
  <a class="navbar-item" href="https://www.yuque.com/zhoutao123" target="_blank">
    语雀
  </a>
  <a class="navbar-item" href="${path}/page/blog">
    博客
  </a>
  <a class="navbar-item hide" href="${path}/page/code/123">
    代码
  </a>
  <a class="navbar-item hide" href="${path}/page/link.html">
    链接
  </a>
  <a class="navbar-item" href="${path}/page/about.html">
    关于
  </a>
</div>


<style>


  .hide{
    display: none;
  }

  <#--  手机界面配置-->
  @media screen and (max-width: 1200px) {

    #pcMenu {
      display: none;
    }

    #mobileMenu {
      display: block;
      background-color: #4a4a4a;
    }

    #mobileMenuDiv {
      display: flex;
      display: -webkit-flex;
    }

    #popMenu {
      height: fit-content;
      display: block;
      z-index: 1000;
      position: absolute;
      top: 50px;
      width: 100%;
      left: 1px;
      background-color: white;
      min-height: 300px;
      box-shadow: 1px 1px 10px 1px #515a6e;
    }
  }


  @media screen and (min-width: 1200px) {


    #pcMenu {
      display: block;
    }

    #mobileMenu {
      display: none;
    }

    .navbar {
      position: fixed;
      width: 100%;
      z-index: 100;
    }
  }

  a.navbar-item:hover {
    background-color: rgba(0, 0, 0, 0) !important;
  }


  .navbar-end {
    align-items: center !important;
  }
</style>


<script>

  for (let ele in document.getElementsByClassName('navbar-item')) {
    ele.onclick = disablePopMenu();
  }

  function actionPupMenu() {
    let ele = document.getElementById('popMenu');
    if (getComputedStyle(ele, false)['display'] === 'none') {
      ele.style.display = 'block';
    } else {
      ele.style.display = 'none';
    }

  }

  function disablePopMenu() {
    document.getElementById('popMenu').style.display = 'none';
  }

</script>
