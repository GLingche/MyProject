<template>
    <div class="m_top">
        <h2><van-icon class-prefix="my-icon" name="extra" />灵车出行</h2>
		<ul>
			<li>
				<select @click="loginShow">
						<option v-for="(v,index) in range" :key="index" :value="v" >{{v}}</option>
				</select>
			</li>
			<li>
				<select @click="loginShow">
						<option v-for="(v,index) in record" :key="index" :value="v" >{{v}}</option>
				</select>
			</li>
		</ul>
    </div>
</template>

<script>
import { getCurrentInstance ,reactive} from "vue";

export default {
	name:'AdminBanner',
	emits:['click'],//vue3声明自定义click事件，覆盖默认行为，native被弃用
	setup(){
		const {proxy} = getCurrentInstance();//获取当前vue对象的代理实例
		let range = reactive(["扩大范围","缩短范围"])
		let record = reactive(["导出乘客信息","导出司机信息"])

		return {range,record}	
	}
}
</script>

<style scoped>
    	.m_top{
		height:70px;
		width:100%;
		border-bottom:0.5px solid gray;
		background: rgba(255, 255, 255, 0.2);
	}
	.m_view{
		position: absulote;	
		height:600px;
	}	
	h2{
		padding-right: 3px;
		float:left;
		font-family:'Times New Roman', Times, serif;
		font-style:italic;
		font-size:40px;
		line-height:40px;
	 }
	li{
		font-size:30px;
		float:right;
    	padding:0 19px;
    	line-height:70px;
   		color:#000;
    	cursor:pointer;
	}
	li:hover{
	background:#000;
    text-decoration:none;
    color:#ddd;
	}
	select{
		background: rgba(255, 255, 255, 0.2);
		border:none;
	}
	option{
		background: rgba(255, 255, 255, 0.2);
	}

	/* 自定义Icon */
  @font-face{
    font-family:'my-icon';
    src:url('../assets/iconfont.ttf') format('truetype')
  }

  .my-icon{
     font-family: "my-icon" !important;
	 color:black;
  	 font-size: 45px;
  	 font-style: italic;
     padding-right: 10px;
 	 -webkit-font-smoothing: antialiased;
  	 -moz-osx-font-smoothing: grayscale;
  }

  .my-icon-extra::before{
   content: "\e694";
  }
</style>