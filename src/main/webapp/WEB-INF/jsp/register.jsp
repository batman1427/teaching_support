<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="0"> 
<title>教学支持系统注册</title> 
<script type="text/javascript">
	function beforeSubmit(form){
		if(form.password.value!=form.checkpass.value){
			alert('两次输入的密码不一致!');
			form.password.focus();
			return false;
		}
		if(form.sid.value.length!=9){
			alert('学号格式不正确(9位)!');
			form.sid.focus();
			return false;
		} 
		if(form.gid.value=="0"){
			alert('请选择分组!');
			form.gid.focus();
			return false;
		} 
	}
</script>
<link href="css/register.css" type="text/css" rel="stylesheet"> 
</head> 
<body> 
<div class="register">
    <div class="message">教学支持系统注册</div>
    <div id="darkbannerwrap"></div>
    <form:form commandName="user_register" action="register" method="post" onsubmit="return beforeSubmit(this);">
		<form:input path="username" id="username" placeholder="用户名" required="true"/>
		<hr class="hr15">
		<form:input path="password" id="password" placeholder="密码" required="true" type="password"/>
		<hr class="hr15">
		<input name="checkpass"  id="checkpass" placeholder="确认密码" required="true" type="password">
		<hr class="hr15">
		<form:input path="name" id="name" placeholder="姓名" required="true"/>
		<hr class="hr15">
		<form:input path="sid" id="sid" placeholder="学号" required="true"/>
		<hr class="hr15">
		<form:select path="gid" id="gid" name="group">
		<option value=0>选择分组</option> 
		<option value=1>1</option>
		<option value=2>2</option>
		<option value=3>3</option>
		<option value=4>4</option>
		<option value=5>5</option>
		<option value=6>6</option>
		<option value=7>7</option>
		<option value=8>8</option>
		<option value=9>9</option>
		<option value=10>10</option>
		<option value=11>11</option>
		<option value=12>12</option>
		<option value=13>13</option>
		<option value=14>14</option>
		<option value=15>15</option>
		<option value=16>16</option>
		<option value=17>17</option>
		<option value=18>18</option>
		<option value=19>19</option>
		<option value=20>20</option>
		<option value=21>21</option>
		<option value=22>22</option>
		<option value=23>23</option>
		<option value=24>24</option>
		<option value=25>25</option>
		<option value=26>26</option>
		<option value=27>27</option>
		<option value=28>28</option>
		<option value=29>29</option>
		<option value=30>30</option>
		<option value=31>31</option>
		<option value=32>32</option>
		<option value=33>33</option>
		<option value=34>34</option>
		<option value=35>35</option>
		<option value=36>36</option>
		<option value=37>37</option>
		<option value=38>38</option>
		<option value=39>39</option>
		<option value=40>40</option>
		<option value=41>41</option>
		<option value=42>42</option>
		<option value=43>43</option>
		<option value=44>44</option>
		<option value=45>45</option>
		<option value=46>46</option>
		<option value=47>47</option>
		<option value=48>48</option>
		<option value=49>49</option>
		<option value=50>50</option>
		<option value=51>51</option>
		<option value=52>52</option>
		<option value=53>53</option>
		<option value=54>54</option>
		<option value=55>55</option>
		<option value=56>56</option>
		<option value=57>57</option>
		<option value=58>58</option>
		<option value=59>59</option>
		<option value=60>60</option>
		</form:select>
		<hr class="hr15">
		<input value="注册" style="width:100%;" type="submit">
		<hr class="hr20">
		<a href="sign_in">直接登录</a>
	</form:form>

	
</div>
</body>
</html>