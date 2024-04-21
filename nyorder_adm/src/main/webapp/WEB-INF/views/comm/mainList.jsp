<!-- 
	@File Name: mainList
	@File 설명 : 메인
	@UI ID : 퍼블리싱 html 에 있는 아이디 밖아주세요
	@작성일 : 2022.01.03
	@작성자 : 누군가
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
var grid;
$(document).ready(function(){

	
});
</script>
<style>
	html, body{width:100%; height:100%; min-width:1680px; overflow-y:auto;}
	.contentWrap .rContentBox{overflow-y:inherit;}
	.guide .contentWrap{width:1600px; margin:0 auto;}
	.comonWrap{margin-bottom:30px;}
	.comonWrap > h3{background:#222; color:#fff; padding:5px 20px; margin-bottom:10px; border-radius:6px 6px 0 0;}
	.comonWrap .sample{padding:10px 0}
	.comonWrap .exType{margin-top:20px;}
	.comonWrap .exType h5{margin-bottom:5px;}
	.comonWrap .exTxt{padding:20px; background:#f2f2f2; font-size:14px}
	.comonWrap .exTxt ul li{line-height:26px;}
	.exTypeWrap{position:relative;}
	.exTypeWrap a.linkGo{position:absolute; right:-35px; top:0; width:18px; height:18px; background:#bebebe; border-radius:100%; background:#eee url("../images/go.png") center center no-repeat; border:1px solid #d5d5d5}
</style>
<div class="content">
	
	<!-- Button -->
	<div class="comonWrap">
		<h3>Button Style</h3>
   
		<div class="sample">
			<!-- <h4>조회영역에 들어가는 경우</h4> -->

			<button type="button" name="" class="comBtn" id="inp_name01">조회</button>

			<button type="button" name="" class="inquBtn">저장</button>

			<button type="button" name="" class="comBtn small">일괄반영</button>
		</div>

		<div class="exTxt">
			<ul>
				<li>
					* 기본 버튼 : class="comBtn"
				</li>
				<li>
					* 기능(저장) 버튼 : class="inquBtn"
				</li>
				<li>
					* 컨텐츠에 들어가는 세로 28px 버튼 : class="comBtn small"
				</li>
			</ul> 
		</div>

		<div class="exType">
			<!-- 조회 Type01 -->
			<h5>[ 예시 ]</h5>
			<div class="exTypeWrap">
				<div class="inquiryBox">
					<dl>
						<dt>권한</dt>
						<dd>
							<select name="" class="sel">
								<option>전체</option>
								<option>점주</option>
								<option>판매원</option>
								<option>판촉사원</option>
								<option>총무</option>
								<option>주간판매점</option>
							</select>
						</dd>
						<dt>상태</dt>
						<dd>
							<select name="" class="sel w160">
								<option>전체</option>
								<option>사용</option>
								<option>중지</option>
							</select>
						</dd>
						<dt>성명</dt>
						<dd>
							<input type="text" id="inp_name01" class="inp" value="" title="성명 입력">
						</dd>
						<dd>
							<button type="button" name="" class="comBtn" id="inp_name01">조회</button>
						</dd>
					</dl>

					<div class="btnGroup right">
						<button type="button" name="" class="comBtn">신규</button>
						<button type="button" name="" class="inquBtn">저장</button>
					</div>
				</div>

				<a href="UI-PMYP-0301.html" class="linkGo" target="_blank"></a>
			</div>
			<!-- 조회 Type01 -->

			<!-- 조회 Type02 -->
			<div class="exTypeWrap">
				<div class="inquiryBox">
					<dl>
						<dt>주문일</dt>
						<dd>
							<div class="formWrap">			
								<div class="dateWrap">
									<input type="text" name="date" value="10/24/1984" class="inp" id="datepicker">
									<button type="button" class="datepickerBtn" title="날짜입력"></button>
								</div>						
																
								<div class="checkGroup">
									<input type="checkbox" id="plus1" class="type01" checked=""><label for="plus1"><span>+1일 제품</span></label>
									<input type="checkbox" id="plus2" class="type01"><label for="plus2"><span>+2일 제품</span></label>
									<input type="checkbox" id="plus3" class="type01"><label for="plus3"><span>+3일 제품</span></label>
								</div>

								<button type="button" name="" class="comBtn" id="inp_name01">조회</button>
							</div>
						</dd>
					</dl>

					<div class="btnGroup right">
						<button type="button" name="" class="comBtn">이력보기</button>
						<button type="button" name="" class="inquBtn">전송</button>
						<button type="button" name="" class="inquBtn">저장</button>
					</div>
				</div>

				<a href="UI-PORD-0101.html" class="linkGo" target="_blank"></a>
			</div>
			<!-- 조회 Type02 -->

			<!-- 조회 Type03 -->
			<div class="exTypeWrap">
				<div class="inquiryBox type03">
					<dl>
						<dt>기준일자</dt>
						<dd>
							<div class="formWrap">			
								<div class="dateWrap">
									<input type="text" name="date" value="10/24/1984" class="inp"> 
									<button type="button" class="datepickerBtn" title="날짜입력"></button>
								</div>						
																
								<div class="checkGroup">
									<input type="radio" name="date" id="date1" class="type01" checked=""><label for="date1"><span>배송일</span></label>
									<input type="radio" name="date" id="date2" class="type01"><label for="date2"><span>주문일</span></label>
								</div>

								<button type="button" name="" class="comBtn" id="inp_name01">조회</button>
							</div>
						</dd>
					</dl>
				</div>

				<a href="UI-PORD-0201.html" class="linkGo" target="_blank"></a>
			</div>
			<!-- 조회 Type03 -->

			<!-- 조회 Type04 -->
			<div class="exTypeWrap">
				<div class="inquiryBox type02">
					<dl>
						<dt>조회월</dt>
						<dd>
							<div class="formWrap">			
								<div class="dateWrap">
									<input type="text" name="date" value="10/24/1984" class="inp" id="datepicker">
									<button type="button" class="datepickerBtn" title="날짜입력"></button>
								</div>						
																
								<div class="checkGroup">
									<input type="checkbox" id="return1" class="type01" checked=""><label for="return1"><span>반품 정보</span></label>
									<input type="checkbox" id="return2" class="type01"><label for="return2"><span>반송 정보</span></label>
								</div>
							</div>
						</dd>
						<dt>제품</dt>
						<dd>
							<div class="formWrap">
								<input type="text" id="inp_product01" class="inp w120 mr10" value="" name="" placeholder="제품코드">
								<input type="text" id="inp_product02" class="inp w160" value="" name="" placeholder="제품명">
							</div>
						</dd>
						<dt>입력경로</dt>
						<dd>
							<select name="" class="sel w120 mr7" id="sel_route">
								<option>전체</option>
								<option>주문시스템</option>
								<option>ERP</option>
								<option>영업모바일</option>
							</select>

							<button type="button" name="" class="comBtn" id="inp_name01">조회</button>
						</dd>
					</dl>

					<div class="btnGroup right">
						<button type="button" name="" class="comBtn">반품등록</button>
					</div>
				</div>

				<a href="UI-PORD-0301.html" class="linkGo" target="_blank"></a>
			</div>
			<!-- 조회 Type04 -->
		</div>

		<div class="exTxt">
			<ul>
				<li>
					* 기본 조회 박스 : class="inquiryBox"
				</li>
				<li>
					* 상단 탭을 포함하는 경우 : class="inquiryBox type02"
				</li>
				<li>
					* 하단여백 15px 인 경우 : class="inquiryBox type03"
				</li>
			</ul>
			
		</div>
	</div>
	<!-- Button -->

	<!-- Text -->
	<div class="comonWrap">
		<h3>Text Style</h3>
   
		<div class="sample">
			<div class="txtInfo">
				※ “주문량” 대비 “확정 수량”이나 “배송 수량”이 달라진 제품은 음영으로 표시됩니다.
			</div>
		</div>

		<div class="exType">
			<!-- text Type01 -->
			<!-- <h5>[ 예시 ]</h5>
			<div class="exTypeWrap">
				<a href="UI-PMYP-0301.html" class="linkGo" target="_blank"></a>
			</div> -->
			<!-- text Type01 -->
		</div>

		<div class="exTxt">
			<ul>
				<li>
					* 회색박스 안에 text : class="txtInfo"
				</li>
			</ul>				  
		</div>
	</div>
	<!-- Text -->
	

	<!-- title + align -->
	<div class="comonWrap">
		<h3>Title + align Style</h3>
   
		<div class="sample">
			<div class="titleArea">
				<h3 class="tit01">직원정보</h3>
				<p class="numState">
					<em>총 <span class="pColor01 fb">24</span></em> 건 이 조회되었습니다.
				</p>
			</div>
			

			<div class="titleArea right">
				<h3 class="tit01">대리점 상세 정보</h3>

				<p class="txt01 pColor02">* 정보 수정 사항은 담당사원에게 요청해주세요.</p>
			</div>
		</div>

		<div class="exType">
			<!-- text Type01 -->
			<!-- <h5>[ 예시 ]</h5>
			<div class="exTypeWrap">
				<a href="UI-PMYP-0301.html" class="linkGo" target="_blank"></a>
			</div> -->
			<!-- text Type01 -->
		</div>

		<div class="titleArea right">
			<h3 class="tit01">소제목</h3>

			<p class="txt01"><i class="icoRequir"></i>필수 입력 항목입니다.</p>
		</div>
	</div>
	<!-- title -->

	
	<!-- Table -->
	<div class="comonWrap">
		<h3>Table Style</h3>
   
		<div class="sample">
			<div class="tblWrap type02">
				<table class="tbl">
					<caption>대리점 상세정보</caption>
					<colgroup>
						<col style="width:170px;">
						<col>
						<col style="width:170px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">
								대리점 코드
							</th>
							<td>
								00001212
							</td>
							<th scope="row">
								상호
							</th>
							<td>
								비티소프트
							</td>
						</tr>
						<tr>
							<th scope="row">
								사업자번호
							</th>
							<td>
								210315486665
							</td>
							<th scope="row">
								대표자명
							</th>
							<td>
								이남양
							</td>
						</tr>
						<tr>
							<th scope="row">
								업태
							</th>
							<td>
								도소매
							</td>
							<th scope="row">
								업종
							</th>
							<td>
								음료및유제품
							</td>
						</tr>
						<tr>
							<th scope="row">
								사업장 전화번호
							</th>
							<td>
								02-1588-4888
							</td>
							<th scope="row">
								휴대폰 번호
							</th>
							<td>
								010-0000-0000
							</td>
						</tr>
						<tr>
							<th scope="row">
								팩스
							</th>
							<td>
								02-1588-4887
							</td>
							<th scope="row">
								이메일 주소
							</th>
							<td>
								btsoft@btsoft.kr 
							</td>
						</tr>
						<tr>
							<th scope="row">
								우편번호
							</th>
							<td colspan="3">
								08165
							</td>
						</tr>
						<tr>
							<th scope="row">
								주소
							</th>
							<td>
								서울시 구로구
							</td>
							<th scope="row">
								상세주소
							</th>
							<td>
								에이스테크노타워 8층
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="tblWrap">
				<table class="tbl">
					<caption>직원정보 상세</caption>
					<colgroup>
						<col style="width:170px;">
						<col>
						<col style="width:170px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">
								<label for="inp_name02" class="required" title="필수입력">성명</label>
							</th>
							<td>
								<input type="text" id="inp_name02" class="inp" value="" name="">
							</td>
							<th scope="row">
								<label for="inp_employee_code">직원코드</label>
							</th>
							<td>
								-
							</td>
						</tr>
						<tr>
							<th scope="row">
								<label for="inp_phone" class="required" title="필수입력">휴대폰 번호</label>
							</th>
							<td>
								<input type="text" id="inp_phone" class="inp" value="" name="">
							</td>
							<th scope="row">
								<label for="sel_authority">권한</label>
							</th>
							<td>
								<select name="" class="sel w160" id="sel_authority">
									<option>전체</option>
									<option>점주</option>
									<option>판매원</option>
									<option>판촉사원</option>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<label for="inp_em_contact01">비상연락처1</label>
							</th>
							<td>
								<input type="text" id="inp_em_contact01" class="inp">
							</td>
							<th scope="row">
								<label for="sel_state">상태</label>
							</th>
							<td>
								<select name="" class="sel w160" id="sel_state">
									<option>전체</option>
									<option>사용</option>
									<option>중지</option>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<label for="inp_em_contact02">비상연락처2</label>
							</th>
							<td>
								<input type="text" id="inp_em_contact02" class="inp">
							</td>
							<th scope="row">
								<label for="inp_email01">이메일 주소</label>
							</th>
							<td>
								<div class="formWrap email">
									<input type="text" id="inp_email01" class="inp w160"> <span class="divi"> @ </span>
									<input type="text" id="inp_email02" class="inp mr10">

									<select name="" class="sel w160" id="sel_email">
										<option>직접입력</option>
										<option>naver.com</option>
										<option>google.com</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<label for="inp_post_num">우편번호</label>
							</th>
							<td colspan="3">
								<div class="postWrap w200">
									<input type="text" id="inp_post_num" class="inp">
									<button type="button" class="postBtn" title="우편번호 찾기"></a>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<label for="inp_address">주소</label>
							</th>
							<td>
								<input type="text" id="inp_address" class="inp">
							</td>
							<th scope="row">
								<label for="inp_address_add">상세주소</label>
							</th>
							<td>
								<input type="text" id="inp_address_add" class="inp">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
		</div>

		<div class="exTxt">
			<ul>
				<li>
					* 기본 테이블 : class="tbl"
				</li>
				<li>
					* col th 사이즈 : width = 170px
				</li>
				<li>
					* 필수입력 인 경우 : class="required" label 값에 넣어줌
				</li>
			</ul> 
		</div>

		<div class="exType">
			<!-- table Type01 -->
			<h5>[ 예시 ]</h5>
			<div class="exTypeWrap">
				<div class="tblWrap">
					<table class="tbl">
						<caption>CMS / 가상계좌 정보</caption>
						<colgroup>
							<col style="width:170px;">
							<col>
							<col style="width:170px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">
									<label for="inp_id" class="required" title="필수입력">아이디</label>
								</th>
								<td>
									<input type="text" id="inp_id" class="inp" value="" name="">
								</td>
								<th scope="row">
									<label for="inp_password" class="required" title="필수입력">비밀번호</label>
								</th>
								<td>
									<input type="password" id="inp_password" class="inp" value="" name="">
								</td>
							</tr>
							<tr>
								<th scope="row">
									<label for="sel_agreement_date" class="required" title="필수입력">이체약정일</label>
								</th>
								<td>
									<span class="dNum">①</span>
									<select name="" class="sel w80 mr20" id="sel_agreement_date">
										<option>전체</option>
										<option>01일</option>
									</select>

									<span class="dNum">②</span>
									<select name="" class="sel w80 mr20">
										<option>전체</option>
										<option>01일</option>
									</select>

									<span class="dNum">③</span>
									<select name="" class="sel w80 mr20">
										<option>전체</option>
										<option>01일</option>
									</select>

									<span class="dNum">④</span>
									<select name="" class="sel w80">
										<option>전체</option>
										<option>01일</option>
									</select>
								</td>
								<th scope="row">
									<label for="inp_password_com" class="required" title="필수입력">비밀번호 재확인</label>
								</th>
								<td>
									<input type="password" id="inp_password_com" class="inp" value="" name="">
								</td>
							</tr>
							<tr>
								<th scope="row">
									<label for="sel_bankNum04" title="필수입력">은행/계좌번호</label>
								</th>
								<td>
									<div class="formWrap">
										<select name="" class="sel w160 mr7" id="sel_bankNum04">
											<option>전체</option>
											<option>국민은행</option>
											<option>우리은행</option>
										</select>
										<input type="text" id="inp_num03" class="inp" value="" name="">
									</div>
								</td>
								<th scope="row">
									<label for="inp_account_name04" class="required">예금주</label>
								</th>
								<td>
									<input type="text" id="inp_account_name04" class="inp" value="" name="">
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<a href="UI-PMYP-0101.html" class="linkGo" target="_blank"></a>
			</div>
			<!-- table Type01 -->
		</div>
	</div>
	<!-- Table -->
</div>
