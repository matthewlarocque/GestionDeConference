<template>
  <el-card>
    <el-row>
      <el-col :span="24">
        <div id="div1" style="height: 75vh;width:100%;"></div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import axios from 'axios';

export default {
  name: "UserAnalyse",
  mounted() {
    this.init();
  },
  data() {
    return {
      freqData: []
    }
  },
  methods: {
    async init(){
      const res1 = await axios.get('//localhost:9999/analyse/stat_freq');
      this.freqData = res1.data;

      let option1 = {
        title: {
          text: '员工使用会议室次数分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '使用次数',
            type: 'pie',
            radius: '50%',
            data: this.freqData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      let myChart = this.$echarts.init(document.getElementById('div1'));
      window.onresize = myChart.resize;
      myChart.setOption(option1);
    }
  }
}
</script>

<style scoped>

</style>
