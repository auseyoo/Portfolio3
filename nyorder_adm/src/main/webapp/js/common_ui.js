
$(document).ready(function(){
	//lnb
	$('.lnb > ul > li.depth1').click(function(e){		
		//e.preventDefault();	

		if ($(this).hasClass("active")){
			$(this).removeClass("active").find(".depth2").slideUp(300);			
		}else{
			$(".lnb > ul > li.active .depth2").slideUp(300);
			$(".lnb > ul > li.active").removeClass("active");
			$(this).addClass("active").find(".depth2").slideDown(300);

			$(".depth2 a").click(function(){
				//return false;
				//e.stopPropagation();
			});
		}
	});
	
	//sideBar
	$(".sideBtn").click(function(e){
		$(".leftSearBox > input").css("display","none");

		var sideBar = $(".lnbWrap");		
		$(this).toggleClass("open");
		sideBar.toggleClass("fold");
		setTimeout(function(){
			if(sideBar.hasClass("fold")){
				$(".leftSearBox > input, .depth2").hide();
				$(".searBtn").css("position","relative");
				$('.etcMenu li[rel="tab02"]').addClass("active");
				//$(".lnb").css("display","block");
	
				$(".depth1 a").click(function(){
					return false;
				});
			}else{
				$(".leftSearBox > input").show();
				//$('.etcMenu li[rel="tab01"]').trigger("click");
				$(".searBtn").css("position","");	
				$('.etcMenu li[rel="tab02"]').removeClass("active");				
	
				$(".depth1 a").unbind("click");							
			}
			
			if( typeof resizeGrid == 'function' ){
				resizeGrid();
			}
		}, 200);
		
		
	});

	//radio div show/hide
	$('input[type="radio"]').change(function(){
        var rTab = $(this).attr('id');          

		$('#' + rTab + 'Con').show();
		$('#' + rTab + 'Con').siblings('div').hide();

		if(typeof resizeGrid == 'function' ){
			resizeGrid();
		}
    });


	//tabs
	$(".content .tabContent").hide();
    $(".content .tabContent:first").show();

	$(".content .tabsWrap ul.tabs li").click(function (){
        $(".content .tabsWrap ul.tabs li").removeClass("active");
        $(this).addClass("active");
        $(".content .tabsWrap .tabContent").hide();
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn(100); 
		if( typeof resizeGrid == 'function' ){
			resizeGrid();
		}
    });

	$(".lnbTabsWrap > .tabContent").hide();
    $(".lnbTabsWrap > .tabContent:first").show();

	$(".lnbTabsWrap > ul.tabs li").click(function (){
        $(".lnbTabsWrap > ul.tabs li").removeClass("active");
        $(this).addClass("active");
        $(".lnbTabsWrap > .tabContent").hide();
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn(100); 
    });

	$(".favor").click(function(){
		$(this).toggleClass("on");
	});
	

	// 윈도우 리사이징 이벤트
    window.onresize = function(){
		//alert("로딩");
		// 크기가 변경되었을 때 AUIGrid.resize() 함수 호출 
		if(typeof resizeGrid == 'function' ){
			resizeGrid();
		}
    };

	// modal
	bindModal();
	
	//table add
	$(".addTrCon").hide();

	$(".addTr").click(function(){
        $("#addTrCon").append('<table class="tbl type02"><colgroup><col style="width:170px;"><col><col style="width:170px;"><col></colgroup><tbody><tr><th scope="row" class="borNonB"><label for="inp_pCode">제품</label></th><td class="borNonB"><div class="formWrap type02"><input type="text" name="" value="" class="inp w140 mr10" readonly=""><div class="searchWrap w223 mr10"><input type="text" name="" value="" class="inp" disabled=""><button type="button" class="postBtn" name="" title="검색하기"></button></div><button type="button" name="" class="cancelBtn medium">삭제</button></div></td><th scope="row" class="borNonB"><label for="inp_pName">지급일</label></th><td class="borNonB"><div class="dateWrap"><input type="text" name="date" value="10/24/1984" class="inp" id="datepicker04" readonly=""><button type="button" class="datepickerBtn" title="날짜입력" data-target-id="datepicker04"></button></div></td></tr><tr><th scope="row"><label for="inp_qty">수량</label></th><td><div class="formWrap w250"><input type="text" id="inp_qty" class="inp ar" value="" name=""> <span class="unit">개</span></div></td><th scope="row"><label for="inp_price">금액</label></th><td><div class="formWrap w250"><input type="text" id="inp_price" class="inp ar" value="" name=""> <span class="unit">원</span></div></td></tr></tbody></table>');
    });
    $("#addTrCon").on('click','.cancelBtn',function(){
		$(this).parents(".tbl").remove();
    });

	//fileup
  	var fileTarget = $('.filebox > input[type=file]');

    fileTarget.on('change', function(){
        if(window.FileReader){
            var filename = $(this)[0].files[0].name;
        } else {
            var filename = $(this).val().split('/').pop().split('\\').pop();
        }

        $(this).siblings('.upload').val(filename);
    });


	//수금상태
	$(".stateView > ul > li").click(function(){
		$(".stateView > ul > li.active").not(this).removeClass('active');
		$(this).toggleClass('active');
	});  

});


function handleClick(){
	if (typeof myGridID !== "undefined"){
		AUIGrid.resize(myGridID);
	}
}

function bindModal(){
	$('[data-id]').on('click', function(e) {
        e.preventDefault();

        var modalID = $(this).attr('data-id');
        var modalBg = $('.modal_bg');
        var modalPop = $('[data-popup="' + modalID + '"]');
        var modalW = $(modalPop).outerWidth();
        var modalH = $(modalPop).outerHeight();

        modalPop.fadeIn(150);
        modalPop.parent().find(".modal_bg").fadeIn(150);

        $(modalPop).css({top: '50%', left: '50%', marginTop: - modalH / 2, marginLeft: - modalW / 2});

        $('.modalCloseBtn').on('click', function(){
      		var target = $(this).closest(".modal_wrap").data('popup');
      		$('[data-popup="'+target+'"]').fadeOut(100);
      		$(this).closest(".modal_wrap").parent().find(".modal_bg").fadeOut(100);
        });
    });
}
