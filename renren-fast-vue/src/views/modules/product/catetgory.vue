<template>
  <div>
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId"
             :default-expanded-keys="expandedKey">
    <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level < 3"
            type="text"
            size="mini"
            @click="() => append(data)">
            Append
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => edit(data)">
            Edit
          </el-button>
          <el-button
            v-if="node.childNodes.length === 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      width="25%">
      <el-form :model="category">
        <el-form-item label="菜单名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitMenu">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'category',
  data () {
    return {
      category: {name: '', parentCid: 0, catLevel: 0, showStatus: 1, sort: 0, catId: 0, icon: '', productUnit: ''},
      title: '',
      dialogType: '',
      dialogVisible: false,
      expandedKey: [],
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  methods: {
    getMenu () {
      this.$http({
        url: this.$http.adornUrl('/product/category/menu'),
        method: 'get'
      }).then(({data}) => {
        this.menus = data.data
      })
    },

    append (data) {
      this.category.name = ''
      this.category.catId = null
      this.category.showStatus = 1
      this.category.sort = 0
      this.category.icon = ''
      this.category.productUnit = ''
      this.dialogVisible = true
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1

      this.dialogType = 'add'
      this.title = '添加分类'
    },

    edit (data) {
      this.dialogVisible = true

      // 回显
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get'
      }).then(({data}) => {
        this.category.name = data.data.name
        this.category.catId = data.data.catId
        this.category.icon = data.data.icon
        this.category.productUnit = data.data.productUnit

        this.category.parentCid = data.data.parentCid
      })

      this.dialogType = 'edit'
      this.title = '修改分类'
    },

    remove (node, data) {
      this.$confirm(`是否删除 [${data.name}] 菜单?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids = [data.catId]
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(() => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          // 刷新菜单
          this.getMenu()
          this.expandedKey = [node.parent.data.catId]
        })
      }).catch(() => {

      })
    },

    // 添加分类菜单
    submitMenu () {
      if (this.dialogType === 'add') {
        this.addMenu()
      } else if (this.dialogType === 'edit') {
        this.editMenu()
      }
    },

    addMenu () {
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.$message({
          message: '添加成功',
          type: 'success'
        })

        this.dialogVisible = false
        this.getMenu()
        this.expandedKey = [this.category.parentCid]
      })
    },

    editMenu () {
      let {catId, name, icon, productUnit} = this.category
      this.$http({
        url: this.$http.adornUrl('/product/category/update'),
        method: 'post',
        data: this.$http.adornData({catId, name, icon, productUnit}, false)
      }).then(({data}) => {
        this.$message({
          message: '修改成功',
          type: 'success'
        })

        this.dialogVisible = false
        this.getMenu()
        this.expandedKey = [this.category.parentCid]
      })
    }
  },
  activated () {
    this.getMenu()
  }
}
</script>

<style scoped>

</style>
