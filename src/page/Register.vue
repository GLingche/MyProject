<template>
    <van-form  @submit="onSubmit">
  <van-field
    v-for="(v,index) in registerInfo" :key="index" 
    v-model="infoList[index]"
    :name="v"
    :label="v"  
    :placeholder="v"
    :rules="[{ required: true, message: `'请填写${v}'` }]"
  />
    <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">提交</van-button>
    </div>
    </van-form>
</template>

<script>
import { reactive } from "vue";
import { useRouter , useRoute} from 'vue-router'
export default {
    name:'Register',
    props:['registerInfo'],
    setup(props){
    const $router = useRouter()//vue3中使用编程式路由
    let  infoList = reactive([...""])//用于配合v-for遍历input的输入框   

     function  onSubmit(values) {
       
      console.log('submit', values)
      $router.push({
         name:'denglu'
      })
    }
      return {infoList,onSubmit}
    }
}
</script>

<style>

</style>