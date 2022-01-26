<template>
   <van-button   v-bind="customizedAttrs">        
          <slot/>    
        </van-button>
</template>

<script>
export default {
    name: "MyButton",inheritAttrs:false,
    props: {
        size: {
            type: String,
            default: "medium",
        },
    },
    computed: {
        customizedAttrs() {
            return {
                size: "medium",
                // 支持传过来的size覆盖默认的size
                ...this.$attrs,
            };
        },
    },
}
</script>

<style>

</style>