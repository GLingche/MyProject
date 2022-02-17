<template>
    <div class="m_top">
        <h2><van-icon class-prefix="my-icon" name="extra" />灵车出行</h2>
		<ul>
            <li><button class="leave" @click="leave">退出</button></li>
			<li><van-icon class-prefix="my-icon2" name="extra" /></li>
            <li><span v-text="`欢迎您&nbsp${driverInfo.name}`"></span></li>
		</ul>
    </div>
</template>

<script>
import { getCurrentInstance ,ref,reactive}from "vue";
import {driverUnLogin} from '../request/Api'
export default {
    props:['isDriver'],
	name:'DriverBanner',
	setup(props){
		const {proxy} = getCurrentInstance();//获取当前vue对象的代理实例
		let driverName = reactive({name:null})
		let driverInfo = reactive({values:null})
        	
		const leave = () => {
			let	values = driverInfo.values		
			driverUnLogin({
				values
			}).then(res=>{

			})
			proxy.$mybus.emit('leave',props.isDriver.toString())
		}

		return {leave,driverName,driverInfo}	
	},
	mounted() {
		//获取司机的信息
		this.$mybus.on('searchDriverInfo',(info)=>{
			this.driverInfo.name = info.name
			this.driverInfo.values = info
		})
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
    	
	}
 
	select{
		background: rgba(255, 255, 255, 0.2);
		border:none;
	}
	option{
		background: rgba(255, 255, 255, 0.2);
	}
	.leave{
         cursor:pointer;
		background: rgba(255, 255, 255, 0.2);
		border:none;
	}

	/* 自定义Icon 1 */
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

/* 自定义Icon 2*/
  @font-face {
  font-family: "my-icon2"; /* Project id  */
  src: url('../assets/incontff/iconfont.ttf') format('truetype');
}

.my-icon2{
   font-family: "my-icon2" !important;
	 color:black;
  	 font-size: 45px;
  	 font-style: normal;
     padding-right: 10px;
 	 -webkit-font-smoothing: antialiased;
  	 -moz-osx-font-smoothing: grayscale;
}

.my-icon2-extra::before {
   content: "\f012d";
}
</style>