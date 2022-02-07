<template>
  <div class="map">
    <router-view></router-view> 
      <div class="sites" v-for="(item,index) in sites" :key="index" :style="location(item.x,item.y)">
        <van-icon name="location-o" color="#f40" size="40"/>
        <span>{{item.name}}</span>
      </div>
      <div class="driver" v-for="(item,index) in dirver" :key="index" :style="activeDriver(item.x,item.y,item.isService)" ><h2 class="driverAge">{{item.driverAge}}</h2></div>
      <div class="range" v-for="(item,index) in passenger" :key="index" :style="region(item.x,item.y,item.Rwidth,item.Rheight,item.trigger)" v-show="!item.isServiced">
        <div class="passenger" :style="updateMargin()"></div>
      </div>
      <div class="defualtRange"></div>
 </div>
 <div >
   <van-form @submit="onSubmit">
  <h3>🟢<h2>{{beaginSite.name}}</h2></h3>
  <van-field
    class="MyInput"
    v-model="endSite"
    type="text"
    name="目的地"
    label-width="2em"
    label="🟠"
    placeholder="输入您的目的地"
    :rules="[{ required: true, message: '请输入您的目的地' }]"
  />
  <div style="margin: 16px;">
    <van-button round block type="info" native-type="submit">确定</van-button>
  </div>
</van-form>
 </div>
</template>

<script>
import {reactive,ref,watchEffect} from 'vue'
import { Toast } from 'vant';
import { useRouter} from 'vue-router'
export default {
    name:"Start",
    props:['loginStatus'],
    setup(props){
      const $router = useRouter()//vue3中使用编程式路由
      let dirver = reactive([])
      let passenger = reactive([])
      let beaginSite = ref()
      let endSite = ref()
      let tempSite = reactive({name:'正在获取您的地理位置....'})
      let sites = reactive([{name:'花都广场',x:470,y:230},{name:'学府路',y:100,x:200},{name:'汇通广场',y:100,x:600},
      {name:'中西结合医院',y:300,x:200},{name:'白云机场',y:420,x:900}])

    
      //元素的相对位置
      function location(x,y){
         return {
           left:  x + "px",
           top: y + "px",
         }
      }

    
      //设置司机随机移动的行为
      function timer(){
       for(let i = 0;i<dirver.length;i++){
        dirver[i].time = setInterval(()=>{
          dirver[i].x = Math.floor(Math.random()*(820-150)) + 150;
          dirver[i].y = Math.floor(Math.random()*(420-50)) + 50;  
      },3000)
       }
    }

   //清除所有定时器
   function claerTimes(){
      for(let dirver of dirver){
         clearTimeout(dirver.time)
      }
   } 


      //乘客搜索范围
      function region(x,y,Rwidth,Rheight,trigger){
        if(!trigger)
          return{
           borderColor: '#52af4a',
           left:  x + "px",
           top: y + "px",
           width: Rwidth + 'px',
           height: Rheight + 'px',
           borderWidth: 1 + 'px',
           borderStyle: 'dashed', 
          }
          return {
           borderColor: 'red',
           left:  x + "px",
           top: y + "px",
           width: Rwidth + 'px',
           height: Rheight + 'px',
           borderWidth: 1 + 'px',
           borderStyle: 'dashed',
          }
      }
  
      
      //是否接到乘客样式变化
      function activeDriver(x,y,val){
        if(!val)
          return {
           left:  x + "px",
           top: y + "px",
           backgroundColor:"rgb(105, 138, 230)"
          }
          return {
           left:  x + "px",
           top: y + "px",
           backgroundColor:"red"
           }
      }

      
  //计算动态变化的margin值
  function getMargin(){
        
/** 由于第一次获取margin值时对象中还未有该属性，可以用以下两种方法解决 
 *1、if(typeof a==='undefined'){
             console.log("#$#$")
         }
  2、try catch 
 * 
*/       
     let marginTop,marginLeft
     try{
         marginTop = (passenger[0].Rheight-30)/2 
         marginLeft = (passenger[0].Rwidth-30)/2  
     }catch(error){
         marginTop = 65
         marginLeft = 65
     } 

     let margin = {marginTop,marginLeft} 
    
     return margin 
  }
  
  
  //乘客的margin变化
  function updateMargin(){
      return{
        marginTop: getMargin().marginTop + 'px',
        marginLeft: getMargin().marginLeft + 'px'
      }
  }

   //延长搜索范围
    function extend(){
         let Ox = passenger[0].x + getMargin().marginLeft
         let Oy = passenger[0].y + getMargin().marginTop   

         passenger[0].Rwidth*=1.5
         passenger[0].Rheight*=1.5

         passenger[0].x = Ox - getMargin().marginLeft
         passenger[0].y = Oy - getMargin().marginTop
    }

  //检查乘客输入的地址 并返回该地址的坐标
  function check(){    
      let end = sites.filter((item,index,array) => endSite.value == item.name )//返回的对象会自动转化为Array类型
      console.log(end[0].x)
      let lot = {endX:end[0].x,endY:end[0].y}
      return lot;
  }


    /**
     * 判断司机是否在乘客约车范围
     * 若是：则将其isService属性置为true
     */
    function search(unwatch,preseconds,watchTime){
        let updateseconds = Date.now()

        if(updateseconds-preseconds>2000){
            extend()//延长搜索范围
            preseconds = updateseconds
        }
        Toast('正在为您寻找车辆')
         let radius = passenger[0].Rwidth/2
          //乘客的初始位置
          let lotX = passenger[0].x + getMargin().marginLeft
          let lotY = passenger[0].y + getMargin().marginTop
          //vue2中由于代理的映射需要时间去完全映射数组里所有的对象  用setTimeout可以解决一开始undefine的问题   setTimeout(()=>{})
          
            for(let i = 0;i<3;i++){
                let dx = lotX > dirver[i].x?lotX - dirver[i].x : dirver[i].x - lotX
                let dy = lotY > dirver[i].y?lotY - dirver[i].y : dirver[i].y - lotY 
                let distance = Math.floor(Math.hypot(dx,dy))  // 函数返回所有平方英寸的平方根
                if(distance<=radius&&watchTime>=1){
                  // console.log("successful")
                  Toast.clear()
                  unwatch()//取消监视
                  dirver[i].isService = true
                  clearInterval(dirver[i].time)
                  $router.push({
                        name:'jieshao',
                        params:{
                          index:i
                        }
                      })  
                  break;
                }            
            } 
    }

    //出发前往目的地
    function start(index){
      passenger[0].isServiced = true;

      let useTime = Math.floor(Math.random()*(5000-3000)) +3000//随机用车时间(3s-5s)
      setTimeout(()=>{
        
         //恢复乘客初始搜索范围
          passenger[0].Rwidth = 160
          passenger[0].Rheight = 160
          //移动乘客的位置
          passenger[0].x = check().endX - getMargin().marginLeft
          passenger[0].y = check().endY - getMargin().marginTop
          //恢复乘客默认状态
          passenger[0].isServiced = false
          passenger[0].trigger = false

          //移动司机的位置
          dirver[index].x = check().endX
          dirver[index].y = check().endY  
          dirver[index].isService = false
          claerTimes()//清除所有定时器
          timer()//重新开启定时器
          alert('即将到达目的地，注意检查随身物品，准备下车')
      },useTime)
    }

     //触发事件按钮 
  function  onSubmit() {
      let isRule = sites.some((item,index,array) => endSite.value == item.name)//检查用户的输入
      
      if(isRule){
      passenger[0].trigger = true//触发车辆搜索事件
      let preseconds = Date.now()
      let watchTime = 0;//标识数据监测的次数，确保其监测次数至少>=2(解决司机的位置一开始就在乘客搜索范围的场景)

      //watchEffect可以智能的监测你想要监测的数据  
      let unwatch =  watchEffect(()=>search(unwatch,preseconds,watchTime++))//传入自身的取消监视函数
      }

      else{
        alert('您输入的地址不在服务区哦，请重新输入')
      }
    }

      return {
        sites,location,dirver,onSubmit,passenger,beaginSite,endSite,tempSite,updateMargin,region,getMargin,activeDriver,start,timer
      }
    },
    
  created() {
      //初始乘客位置检索
      let randomIndex = Math.floor(Math.random()*5) //[0,5)
      this.beaginSite = this.tempSite
      this.passenger.push({x:this.sites[randomIndex].x-this.getMargin().marginLeft,y:this.sites[randomIndex].y-this.getMargin().marginTop,Rwidth:160,Rheight:160,isServiced:false,trigger:false})

      //初始化三个司机
      for(let i =0;i<3;i++){
          let dirverX = Math.floor(Math.random()*(820-150)) + 150;
          let dirverY = Math.floor(Math.random()*(420-50)) + 50;
          let driverAge = Math.floor(Math.random()*6) + 3
          let time
          this.dirver.push({x:dirverX,y:dirverY,time,driverAge,isService:false})          
       }

       
    setTimeout(()=>{
        this.beaginSite = this.sites[randomIndex]
    },2000)

  },
  mounted(){
    this.$mybus.on('start',this.start)//全局事件总线注册
    
    //挂载后(页面渲染完成后)开启定时器
    this.timer()   
    // clearInterval(dirver[0].time)

  }

}
</script>

<style  scoped>
  *{
    padding:0;
    margin:0;
  }
  .map{
      position: relative;
      background-color: #52af4a;
      width:1000px;
      height:500px;
      border:1px solid black;
  }
  .map .sites{
     position:absolute;
     top:420px;
     left:900px;
     width:50px;
     height:60px;
     box-sizing: border-box;
  }
  .defualtRange{
     position:relative;
     top:50px;
     left:150px;
     width:700px;
     height:400px;
     border:1px dashed #f40;
  }
  .big-Range{
    position:relative;
     top:0;
     left:0;
     width:1000px;
     height:500px;
     border:1px dashed #f40;
  }
  .map .driver{
      top:420px;/*50~420*/
      left:820px;/*150~820*/
      position:absolute;
      border:1px solid black;
      box-sizing:border-box;
      width:30px;
      height:30px;
      border-radius: 50%;
  }

  .map .range{
    position: absolute;
    box-sizing:border-box;
    border-radius: 50%;
  }    

  .map .passenger{
    background-color: rgb(17, 223, 86);
    position: absolute;
    border:1px solid black;
    box-sizing:border-box;
    width:30px;
    height:30px;
    border-radius: 50%;
  }

  h3{
    padding:10px 0 ;
    color: rgb(24, 207, 70);
  } 
  h2{
    display: inline;
    padding:0 10px;
  }
  .MyInput{
    height:100px;
    line-height:60px;
    font-size: 40px;
    padding-top: 3px;
  }

  .driverAge{
    position: relative;
    top:-2px;
    left:-4px;
  }
  
</style>

