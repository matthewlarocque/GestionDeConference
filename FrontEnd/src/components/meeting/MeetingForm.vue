<template>
  <el-dialog :title="title" :visible.sync="visible" width="30%">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="会议室名称" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="会议室规格" prop="type">
        <el-select v-model="form.type" placeholder="请选择规格">
          <el-option value="大型">大型</el-option>
          <el-option value="中型">中型</el-option>
          <el-option value="小型">小型</el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { postAction,getAction} from '@api/manage';
export default {
  name: 'MeetingForm',
  props: {
    dialogFormVisible: Boolean,
    id:String,
    sizePerPage:Number
  },
  watch:{
    dialogFormVisible:{
      handler (newValue, oldValue) {
        this.visible=newValue;
      },
    },
    id:{
      handler (newValue, oldValue) {
        if(newValue!=null){
          this.title='编辑会议室'
        }else{
          this.title='新增会议室'
        }
        getAction('meeting/detail/'+newValue, {}).then((res) => {
          this.form.id=res.data.id;
          this.form.name=res.data.name;
          this.form.type=res.data.type;
        });
      },
    },
  },
  mounted(){
    this.form={
      id:null,
      name: '',
      type:undefined,
    }
  },
  data() {
    return {
      visible:false,
      title:'新增会议室',
      dialogTableVisible: false,
      users:[],
      form: {
        id:null,
        name: '',
        type:undefined,
      },
      rules: {
        name: [
          { required: true, message: '请输入会议室名称', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              postAction('meeting/checkMeeting', this.form).then((res) => {
                if(!res.data.success){
                  return callback(new Error('会议室名称已存在'));
                }else{
                  callback();
                }
              });
            }, trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择规格', trigger: 'change' },
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          postAction('meeting/saveMeeting', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('meetingList',{currentPage:0,sizePerPage:this.sizePerPage})
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
