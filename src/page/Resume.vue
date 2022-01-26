<template>
    <div class="warp">
        <van-icon class-prefix="my-icon" name="extra" />
         <div class="info">
            <span>姓名:fda</span>
             <span>姓名:</span>
              <span>姓名:</span>
               <span>姓名:</span>
                <span>姓名:</span> 
        </div> 

      <van-button type="primary" round size="normal"  to="/"><van-icon name="cross" size="40" /></van-button>
    </div>

</template>

<script>
export default {
    name:"Resume",
    setup(){
        return 
    }
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