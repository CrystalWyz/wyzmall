<template>
  <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId">
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
            v-if="node.childNodes.length === 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
      </span>
  </el-tree>
</template>

<script>
export default {
  name: 'catetgory',
  data () {
    return {
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

    },

    remove (node, data) {
      console.log(node)
      console.log(data)
    }
  },
  activated () {
    this.getMenu()
  }
}
</script>

<style scoped>

</style>
