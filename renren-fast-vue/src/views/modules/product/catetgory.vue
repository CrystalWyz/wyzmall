<template>
  <div>
    <el-switch
      v-model="draggable"
      active-text="开启拖拽"
      inactive-text="关闭拖拽">
    </el-switch>
    <el-button type="danger" round @click="batchDel">批量删除</el-button>
    <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId"
             :default-expanded-keys="expandedKey" :draggable="draggable" @allow-drop="allowDrop" @node-drop="handleDrop"
             ref="menuTree">
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
      maxLevel: 1,
      title: '',
      dialogType: '',
      dialogVisible: false,
      draggable: true,
      expandedKey: [],
      updateNodes: [],
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
    },

    allowDrop (draggingNode, dropNode, type) {
      this.countNodeLevel(draggingNode)
      let depth = Math.abs(this.maxLevel - draggingNode.level) + 1
      if (type === 'inner') {
        return depth + dropNode.level <= 3
      } else {
        return depth + dropNode.parent.level <= 3
      }
    },

    countNodeLevel (draggingNode) {
      if (draggingNode.childNodes != null && draggingNode.childNodes.length > 0) {
        for (let i = 0; i < draggingNode.childNodes.length; i++) {
          if (draggingNode.childNodes[i].level > this.maxLevel) {
            this.maxLevel = draggingNode.childNodes[i].level
          }
          this.countNodeLevel(draggingNode.childNodes[i])
        }
      } else {
        this.maxLevel = draggingNode.catLevel
      }
    },

    handleDrop (draggingNode, dropNode, dropType, ev) {
      let parentId = 0
      let siblings = null
      if (dropType === 'inner') {
        parentId = dropNode.data.catId
        siblings = dropNode.childNodes
      } else {
        parentId = dropNode.parent.data.catId === undefined ? 0 : dropNode.parent.data.catId
        siblings = dropNode.parent.childNodes
      }

      for (let i = 0; i < siblings.length; i++) {
        if (siblings[i].data.catId === draggingNode.data.catId) {
          let catLevel = draggingNode.level
          // 层级变化
          if (siblings[i].level !== draggingNode.level) {
            catLevel = draggingNode.level

            this.updateChildNodeLevel(siblings[i])
          }

          // 处理拖拽节点
          this.updateNodes.push({catId: siblings[i].data.catId, sort: i, parentCid: parentId, catLevel: catLevel})
        } else {
          this.updateNodes.push({catId: siblings[i].data.catId, sort: i})
        }
      }
      // 发送请求
      this.$http({
        url: this.$http.adornUrl('/product/category/update/menu'),
        method: 'post',
        data: this.$http.adornData(this.updateNodes, false)
      }).then(({data}) => {
        this.$message({
          message: '修改成功',
          type: 'success'
        })
      })

      // 刷新菜单
      this.getMenu()
      this.expandedKey = [parentId]

      // 重置
      this.maxLevel = 0
      this.updateNodes = []
    },

    updateChildNodeLevel (child) {
      if (child.childNodes.length > 0) {
        for (let i = 0; i < child.childNodes.length; i++) {
          this.updateNodes.push({catId: child.childNodes[i].data.catId, catLevel: child.childNodes[i].level})
          this.updateChildNodeLevel(child.childNodes[i])
        }
      }
    },

    batchDel () {
      this.$confirm(`是否删除选中的菜单?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let catIds = this.$refs.menuTree.getCheckedNodes().map(e => e.catId)
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(catIds, false)
        }).then(() => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          // 刷新菜单
          this.getMenu()
          this.expandedKey = []
        })
      }).catch(() => {

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
