<template>
  <el-container>
    <el-header style="height: 18vh;">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="员工姓名">
          <el-input v-model="searchObj.username" placeholder="员工姓名"></el-input>
        </el-form-item>
        <el-form-item label="会议室">
          <el-select clearable v-model="searchObj.meetingid" placeholder="会议室">
            <el-option :label="item.name" :value="item.id" v-for="item in meetings" :key="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="预定开始时间大于">
          <el-date-picker
            v-model="searchObj.starttime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预定开始时间小于">
          <el-date-picker
            v-model="searchObj.endtime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
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
          label="员工姓名"
          property="username"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="开始使用时间"
          property="startdatetime"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="结束使用时间"
          property="enddatetime"
          width="150"
        >
        </el-table-column>
        <el-table-column
          label="会议室"
          property="meetingname"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="状态"
          property="status"
        >
          <template slot-scope="scope">
            <el-tag size="medium" :type="scope.row.status === '审核通过' ? 'success' : 'danger'">
              {{ scope.row.status}}
            </el-tag>
            <el-button
              v-if="scope.row.status==='待审'||scope.row.status==='审核未通过'"
              size="mini"
              type="primary"
              style="margin-left: 10px"
              @click="changeStatus(scope.row,'审核通过')"
            >
              通过
            </el-button>
            <el-button
              v-if="scope.row.status==='待审'||scope.row.status==='审核通过'"
              size="mini"
              type="danger"
              style="margin-left: 10px"
              @click="changeStatus(scope.row,'审核未通过')"
            >
              不通过
            </el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
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
      <ScheduleForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:scheduleList="getScheduleList"></ScheduleForm>
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
    <el-dialog title="未通过原因" :visible.sync="dialogFormVisible2" width="40%">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="未通过原因" prop="remark">
          <el-input type="textarea" autosize v-model="form.remark" placeholder="未通过原因"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="changeRemark">确 定</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import { postAction} from '@api/manage';
import ScheduleForm from './ScheduleForm';
export default {
  name: 'ScheduleList',
  components: {ScheduleForm},
  data() {
    return {
      id: null,
      currentId: null,
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
      dialogFormVisible2:false,
      form:{
        remark:''
      },
      rules: {
        remark: [
          { min: 5, max: 200, message: '长度在 5 到 200 个字符', trigger: 'blur' },
        ],
      },
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
      postAction('schedule/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addSchedule(index) {
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
    changeStatus(row,value) {
      if(value==='审核未通过'){
        this.currentId=row.id
        this.dialogFormVisible2=true
      }else{
        postAction('schedule/updateStatus', {id:row.id,status:value}).then((res) => {
          this.getScheduleList({currentPage:this.pageObj.currentPage,sizePerPage:this.pageObj.pageSize})
        });
      }
    },
    changeRemark(){
      postAction('schedule/updateStatus', {id:this.currentId,status:'审核未通过',remark:this.form.remark}).then((res) => {
        this.getScheduleList({currentPage:this.pageObj.currentPage,sizePerPage:this.pageObj.pageSize})
        this.dialogFormVisible2=false
      });
    }
  }
}
</script>

<style scoped>

</style>
