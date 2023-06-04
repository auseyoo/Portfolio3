$(function(){
  // header
  $(window).scroll(function(){
    if($(window).scrollTop() > 50){
      $('header').addClass('dark')
    }
    else {
      $('header').removeClass('dark')
    }
  })

  // $(window).width(function(){
  //   if($(window).windowWidth() >= 1024){
  //     $('gnb').removeClass('active')
  //   }
  //   else {
  //     $('gnb').siblings(this).addClass('active')
  //   }
  // })

  // GNB
  $('.gnb a').click(function(){
    $(this).addClass('active')
    $(this).siblings(this).removeClass('active')
  })
  // Trigger
  $('.trigger').click(function(){
    $('.gnb, html, .overlay').toggleClass('active')
    $(this).toggleClass('active')
  }) 
  // Overlay
  $('.overlay, .gnb a').click(function(){
    $('.gnb, .trigger, body, .overlay').removeClass('active')
  })

  // Modal
  $('.modal-notice').click(function(){
      $(this).siblings('.modal').fadeIn()
      $('html').css({"overflow": "hidden"})
  })
  $('.modal-notice2').click(function(){
    $(this).parent().siblings('.modal').fadeIn()
    $('html').css({"overflow": "hidden"})
})
  $('.btn-close, .modal').click(function(){
      $('.modal').fadeOut()
      $('html').css({"overflow": "auto"})
  })

  // Tab 
  $('.tab-btn a').click(function(){
      $(this).addClass('active')
      $(this).siblings().removeClass('active')
  })

  // Slick
  $('.myslider').slick({
    infinite: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: true,
    autoplay: true,
    autoplaySpeed: 2000,
    speed: 500,
    fade: false,
    cssEase: 'linear',
    responsive: [
      {
        breakpoint: 1024,
        settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
            infinite: true,
            dots: true
          }
      },
      {
        breakpoint: 600,
        settings: {
            slidesToShow: 1,
            slidesToScroll: 1
        }
      },
      {
        breakpoint: 480,
        settings: {
            slidesToShow: 1,
            slidesToScroll: 1
        }
      }
    ]
  });

  // Wow
  wow = new WOW(
    {
      boxClass: 'wow', // default
      mobile: true, // default
      offset: 150, // default
      live: false // default
    }
  )
  wow.init();
})

function gnb_wrap(){
  var windowWidth = $( window ).width();
  if(windowWidth >= 1024) {
  //창 가로 크기가 1024 이상일 경우
    $('gnb').siblings(this).removeClass('active')

  } else {
    $('gnb').siblings(this).addClass('active')
  }
  }