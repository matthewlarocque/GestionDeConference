<template>
  <el-container>
    <el-header style="height: 18vh;">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="会议室">
          <el-select clearable v-model="searchObj.meetingid" placeholder="会议室">
            <el-option :label="item.name" :value="item.id" v-for="item in meetings" :key="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSchedule">我要预定</el-button>
        </el-form-item>
      </el-form>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          label="会议室"
          property="meetingname"
        >
        </el-table-column>
        <el-table-column
          label="开始使用时间"
          property="startdatetime"
        >
        </el-table-column>
        <el-table-column
          label="结束使用时间"
          property="enddatetime"
        >
        </el-table-column>
        <el-table-column
          label="状态"
          property="status"
        >
          <template slot-scope="scope">
            <el-tag size="medium" :type="scope.row.status === '审核通过' ? 'success' : scope.row.status === '待审'?'warning':'danger'">
              {{ scope.row.status}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="备注"
          property="remark"
        >
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <MyScheduleForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:scheduleList="getScheduleList"></MyScheduleForm>
    </el-main>
    <el-footer style="text-align: right">
      <el-pagination
        :page-size="pageObj.pageSize"
        :page-count="pageObj.pagerCount"
        :pager-count="11"
        @prev-click="onPrev"
        @next-click="onNext"
        @current-change="onPrev"
        :hide-on-single-page="true"
        background
        layout="prev, pager, next"
        :total="pageObj.total">
      </el-pagination>
    </el-footer>

  </el-container>
</template>

<script>
import { postAction} from '@api/manage';
import ScheduleForm from './ScheduleForm';
import MyScheduleForm from "./MyScheduleForm";
export default {
  name: 'MyScheduleList',
  components: {MyScheduleForm, ScheduleForm},
  data() {
    return {
      id: null,
      searchObj: {
        starttime: '',
        endtime: '',
        username: '',
        meetingid: undefined
      },
      pageObj: {
        total:0,
        pagerCount: 0,
        pageSize:8,
        currentPage:0
      },
      dialogFormVisible:false,
      tableData: [],
      meetings:[],
    }
  },
  mounted(){
    this.getMeeting()
    this.getScheduleList({currentPage:0,sizePerPage:this.pageObj.pageSize})
  },
  methods: {
    getMeeting(){
      postAction('meeting/getMeeting', {}).then((res) => {
        this.meetings=res.data
      });
    },
    getScheduleList(obj) {
      obj.userid=this.$currentUser.id
      postAction('schedule/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addSchedule(index) {
      if(this.$currentUser.id==null){
        this.$message.success('请先登录')
        return;
      }
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getScheduleList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getScheduleList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    handleEdit(index, row) {
      this.id='';
      this.id=row.id;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    handleDelete(index, row) {
      this.$confirm('确定要删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        postAction('schedule/deleteSchedule', {id:row.id}).then((res) => {
          this.$message.success('删除成功')
          this.onSearch();
        });
      }).catch(() => {
      });
    },
    onSearch() {
      this.getScheduleList({currentPage:0,
        sizePerPage:this.pageObj.pageSize,
        username: this.searchObj.username,
        starttime: this.searchObj.starttime,
        endtime: this.searchObj.endtime,
        meetingid: this.searchObj.meetingid,
      })
    },
  }
}
</script>

<style scoped>

</style>
