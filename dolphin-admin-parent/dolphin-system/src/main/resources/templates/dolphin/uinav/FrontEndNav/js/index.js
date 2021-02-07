window.isClick = false

$(function() {

  // ================================点击导航事件================================
  $('#category').on('click', 'li', function() {
    // 当前处在点击状态
    isClick = true;
    $('#category li').each(function() {
      $(this).removeClass('active')
    })
    $(this).addClass('active')
    // 获取当前楼层名
    var floorId = $(this).attr('id')
    var floorName = floorId + 'Item'
    // 获取楼层高度
    var floorTop = $('#' + floorName).offset().top - 40
    // console.log(floorTop);
    $('html,body')
      .stop()
      .animate(
        {
          scrollTop: floorTop
        },
        800,
        'linear',
        function () {
          setTimeout(function () {
            isClick = false;
          },800)
        }
      )
  })

  //==============================监听页面滚动事件================================
  $(window).scroll(function() {
    // 获取屏幕滚动的高度
    var top = $('html,body').scrollTop() || $(window).scrollTop()
    // 判断页面是否手动滚动
    if (!isClick) {
      $('#mainContent > div').each(function() {
        // 获取当前盒子在浏览器的高度
        var floorHight = $(this).offset().top
        // 获取当前楼层id
        var floorName = $(this).attr('id')
        var floorId = floorName.substring(0, floorName.length - 4)
        // 设置楼层高度范围
        var floorTop = floorHight
        var floorFoot = floorHight + $('#' + floorId).height()
        if (top >= floorTop && top <= 3="" floorfoot)="" {="" $('#category="" li').each(function()="" $(this).removeclass('active')="" })="" 修改顶部导航栏="=========================================" $('#'="" +="" floorid).addclass('active')="" var="" index="$('#category" li').index(="" document.queryselector('#'="" floorid)="" )="" step="document.body.clientWidth" movel="(index" -="" 1)="" *="" (step="" 4)="" 判断是否需要偏移="" if="" (index=""> 1) {
            $('#category ul').scrollLeft(moveL)
          } else {
            $('#category ul').scrollLeft(0)
          }
        }
      })
    } else {
      return false
    }
  })

  // ============================返回顶部业务==============================
  function goTop() {
    let offset = 300,
      offset_opacity = 1200,
      scroll_top_duration = 700,
      $goTopBtn = $('.goTop')

    // c窗口滚动事件
    $(window).scroll(function() {
      $goTopBtn.removeClass('.goTop:hover')
      $(this).scrollTop() > offset
        ? $goTopBtn.addClass('btn-is-visible')
        : $goTopBtn.removeClass('btn-is-visible btn-fade-out')
      if ($(this).scrollTop() > offset_opacity) {
        $goTopBtn.addClass('btn-fade-out')
      }
    })

    // 返回顶部按钮点击事件
    $goTopBtn.on('click', function(event) {
      event.preventDefault()
      $('body,html').animate(
        {
          scrollTop: 0
        },
        scroll_top_duration
      )
    })
  }
})
</=>