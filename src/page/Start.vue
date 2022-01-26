<template>
  <div class="map">
    <router-view></router-view> 
      <div class="sites" v-for="(item,index) in sites" :key="index" :style="location(item.x,item.y)">
        <van-icon name="location-o" color="#f40" size="40"/>
        <span>{{item.name}}</span>
      </div>
      <div class="driver" v-for="(item,index) in dirver" :key="index" :style="activeDriver(item.x,item.y,item.isService)" ><h2 class="driverAge">{{item.driverAge}}</h2></div>
      <div class="range" v-for="(item,index) in passenger" :key="index" :style="location(item.x,item.y)">
        <div class="passenger"></div>
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
      let range = reactive({width:160,height:160})
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

      //是否接到乘客
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
     let marginTop = (range.height-30)/2
     let marginLeft = (range.width-30)/2

     let margin = {marginTop,marginLeft} 

     return margin
  }

    /**
     * 判断司机是否在乘客约车范围
     * 若是：则将其isService属性置为true
     */

    function search(){
         let radius = range.width/2
          //乘客的初始位置
          let lotX = passenger[0].x + getMargin().marginLeft
          let lotY = passenger[0].y + getMargin().marginTop

                //由于代理的映射需要时间去完全映射数组里所有的对象  用setTimeout可以解决一开始undefine的问题
          setTimeout(()=>{
            for(let i = 0;i<3;i++){
                let dx = lotX > dirver[i].x?lotX - dirver[i].x : dirver[i].x - lotX
                let dy = lotY > dirver[i].y?lotY - dirver[i].y : dirver[i].y - lotY 
                let distance = Math.floor(Math.hypot(dx,dy))  // 函数返回所有平方英寸的平方根
                console.log("@@",distance)
                if(distance<=radius){
                  // console.log("successful")
                  passenger[0].isServiced = true;
                  dirver[i].isService = true;
                  break;
                }            
            }
           })
    }

     function  onSubmit(values) {
          Toast('正在为您寻找车辆')

          //watchEffect可以智能的监测你想要监测的数据
          watchEffect(()=>search())

          setTimeout(()=>{
            $router.push({
              name:'jieshao'
          })
          Toast.clear()
        },1000)  
        
         console.log('submit', values);
    }
      

      return {
        sites,location,dirver,onSubmit,passenger,beaginSite,endSite,tempSite,activeDriver,getMargin,range,search
      }
    },
  created() {

      //初始乘客位置检索
      let randomIndex = Math.floor(Math.random()*5) //[0,5)
      this.beaginSite = this.tempSite
      this.passenger.push({x:this.sites[randomIndex].x-this.getMargin().marginLeft,y: this.sites[randomIndex].y-this.getMargin().marginTop,isServiced:false})

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
// watch:{
    
//       dirver:{
//         deep:true,
//         Immediate:false,
//         handler(newVal,oldVal){  
//           let radius = this.range.width/2
//           //乘客的初始位置
//           let lotX = this.passenger[0].x + this.getMargin().marginLeft
//           let lotY = this.passenger[0].y + this.getMargin().marginTop
    
//           //由于代理的映射需要时间去完全映射数组里所有的对象  用setTimeout可以解决一开始undefine的问题
//           setTimeout(()=>{
//             for(let i = 0;i<3;i++){
//                 let dx = lotX > newVal[i].x?lotX - newVal[i].x : newVal[i].x - lotX
//                 let dy = lotY > newVal[i].y?lotY - newVal[i].y : newVal[i].y - lotY 
//                 let distance = Math.floor(Math.hypot(dx,dy))  // 函数返回所有平方英寸的平方根
//                 // console.log("@@",distance)
//                 if(distance<=radius){
//                   // console.log("successful")
//                   this.passenger[0].isServiced = true;
//                   newVal[i].isService = true;
//                   break;
//                 }            
//             }
//            })
//         },
      
//       }
//   },

  mounted(){
       let dirver = this.dirver;
      //设置司机随机移动的行为
      function timer(){
       for(let i = 0;i<dirver.length;i++){
        dirver[i].time = setInterval(()=>{
          dirver[i].x = Math.floor(Math.random()*(820-150)) + 150;
          dirver[i].y = Math.floor(Math.random()*(420-50)) + 50;  
      },3000)
       }
    }
    
    timer()   
   
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
      background-color:rgb(105, 138, 230);
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
    border:1px dashed black;
    box-sizing:border-box;
    width:160px;
    height:160px;
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
    margin-top:65px;
    margin-left:65px;
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

