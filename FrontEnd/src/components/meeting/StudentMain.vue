<template>
  <el-container>
    <StudentLeft></StudentLeft>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import StudentLeft from './StudentLeft';
export default {
  name: 'StudentMain',
  components: {StudentLeft},
  props: {
    msg: String
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
