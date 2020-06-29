<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>影子不长 | 燕归来兮</title>
    <#include "base/key.ftl">
  <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/style1.css"/>
  <script src="/js/modernizr-custom.js"></script>
</head>
<#include "base/header.ftl">
<div style="height: 40px;width: 100%"></div>
<body class="demo-1">
<div class="container">
  <div class="content">
    <div class="grid">
        <#list  pictureList as picture>
          <div class="grid__item " data-size="1280x853"><a href="${picture.url}" class="img-wrap">
              <img
                  class="pic pic0"
                  src="${picture.url}?imageView2/3/w/200/h/100/q/80">
              <div class="description description--grid"> Quixotic</div>
            </a></div>
        </#list>
    </div>
    <div class="preview">
      <button class="action action--close"><i class="fa fa-times">x</i><span
            class="text-hidden">Close</span></button>
      <div class="description description--preview"></div>
    </div>
  </div>
</div>


<script src="/js/imagesloaded.pkgd.min.js"></script>
<script src="/js/masonry.pkgd.min.js"></script>
<script src="/js/classie.js"></script>
<script src="/js/main.js"></script>
<script src="/js/lazy.js"></script>
<script>
  $(function () {
    $("img.lazy").lazyload({effect: "fadeIn"});
  });

  (function () {
    // create SVG circle overlay and append it to the preview element
    function createCircleOverlay(previewEl) {
      var dummy = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
      dummy.setAttributeNS(null, 'version', '1.1');
      dummy.setAttributeNS(null, 'width', '100%');
      dummy.setAttributeNS(null, 'height', '100%');
      dummy.setAttributeNS(null, 'class', 'overlay');
      var g = document.createElementNS('http://www.w3.org/2000/svg', 'g');
      var circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
      circle.setAttributeNS(null, 'cx', 0);
      circle.setAttributeNS(null, 'cy', 0);
      circle.setAttributeNS(null, 'r',
          Math.sqrt(Math.pow(previewEl.offsetWidth, 2) + Math.pow(previewEl
              .offsetHeight, 2)));
      dummy.appendChild(g);
      g.appendChild(circle);
      previewEl.appendChild(dummy);
    }

    new GridFx(document.querySelector('.grid'), {
      onInit: function (instance) {
        createCircleOverlay(instance.previewEl);
      },
      onResize: function (instance) {
        instance.previewEl.querySelector('svg circle').setAttributeNS(null, 'r', Math.sqrt(
            Math.pow(instance.previewEl.offsetWidth, 2) + Math.pow(instance
                .previewEl.offsetHeight, 2)));
      },
      onOpenItem: function (instance, item) {
        // item's image
        var gridImg = item.querySelector('img'),
            gridImgOffset = gridImg.getBoundingClientRect(),
            win = {
              width: document.documentElement.clientWidth,
              height: window.innerHeight
            },
            SVGCircleGroupEl = instance.previewEl.querySelector('svg > g'),
            SVGCircleEl = SVGCircleGroupEl.querySelector('circle');

        SVGCircleEl.setAttributeNS(null, 'r', Math.sqrt(Math.pow(instance.previewEl
            .offsetWidth, 2) + Math.pow(instance.previewEl.offsetHeight, 2)));
        // set the transform for the SVG g node. This will animate the circle overlay. The origin of the circle depends on the position of the clicked item.
        if (gridImgOffset.left + gridImg.offsetWidth / 2 < win.width / 2) {
          SVGCircleGroupEl.setAttributeNS(null, 'transform', 'translate(' + win.width +
              ', ' + (gridImgOffset.top + gridImg.offsetHeight / 2 < win.height / 2 ?
                  win.height : 0) + ')');
        } else {
          SVGCircleGroupEl.setAttributeNS(null, 'transform', 'translate(0, ' + (
              gridImgOffset.top + gridImg.offsetHeight / 2 < win.height / 2 ? win
                  .height : 0) + ')');
        }
      }
    });
  })();
</script>

</body>

</html>
