<template>
    <div class="warp">
        <van-icon class-prefix="my-icon" name="extra" />
         <div class="info">
           <span v-for="(item,index) in columnName" :key="index" :value="item">{{item}}{{colunmValues.values[index]}}</span>
        </div> 

      <van-button type="primary" round size="normal"  @click="close"><van-icon name="cross" size="40" /></van-button>
    </div>

</template>

<script>
import { useRouter} from 'vue-router'
import { getCurrentInstance ,reactive} from "vue";
import {searchIntroduce} from '../request/Api'
export default {
    name:"Resume",
    props:['index','carID'],
    setup(props){
        const { proxy } = getCurrentInstance();//获取当前vue对象的代理实例
        const $router = useRouter()//vue3中使用编程式路由
        const columnName = ["姓名:","车辆类型:","车牌号:","颜色:","车龄:"]//司机基本信息
        let colunmValues = reactive({values:[]})//内容
        let carID
        let indexInfo

        //接收栏目内容
        if((carID = props.carID)!==undefined){
            searchIntroduce({
                carID
            }).then(res=>{
                //用迭代器来打平数组
                const iter ={
                *[Symbol.iterator](){
                    yield res[0].name
                    yield res[0].type
                    yield res[0].code
                    yield res[0].color
                    yield res[0].age
                    }
                 }
                colunmValues.values = Array.from(iter)
            })

           indexInfo = {index:props.index,carID:props.carID}
        }

        function close(){
            proxy.$mybus.emit('start',indexInfo)
            $router.push({
			name:'chufa',
		})
    }

        return {close,columnName,colunmValues}
    },
}
</script>

<style scoped>
    *{
        padding:0;
        margin:0;
    }
    .warp{  
        position:absolute;
        left:100px;
        top:40px;
        width: 800px;
        height:400px;    
        border:1px solid black;
        z-index: 1;
        background-color:white;
        /* border-radius: .4em .4em 2em 2em / .4em .4em 3em 3em;   */
        border-radius: 5em / 2em;

        display: flex;
    }

    span{
        padding-top: 20px;
        font-size: 20px;
        font-weight:bold;    
    }
   .info{
       margin:50px 0;
       height:300px;
       width:300px;
       display: flex;
       flex-direction:column;
    }
@font-face {
  font-family: "my-icon"; /* Project id  */
  src: url('../assets/incontff/iconfont.ttf') format('truetype');
}

.my-icon{
  margin:50px 50px;  
  border:1px solid black;
  border-radius: .3em;
  width: 300px;
  height:300px;
  font-family: "my-icon" !important;
  text-align: center;
  line-height: 300px;
  font-size: 200px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.my-icon-extra::before {
   content: "\f012d";
}
</style>