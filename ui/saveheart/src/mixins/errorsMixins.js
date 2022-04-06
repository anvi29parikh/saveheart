export const errorsMixin = {
  methods: {
    handleError (error) {
      if (error && error.response && error.response.status === 500) {
        if (error.response.data && error.response.data.results && error.response.data.results.message) {
          this.$q.notify({
            color: 'negative',
            icon: 'fa fa-times-circle',
            position: 'top-right',
            message: error.response.data.results.message,
            timeout: 3000,
            actions: [
              { label: 'Dismiss', color: 'white' }
            ]
          })
        } else if (error.response.data) {
          this.$q.notify({
            color: 'negative',
            icon: 'fa fa-times-circle',
            position: 'top-right',
            message: error.response.data.message,
            timeout: 3000,
            actions: [
              { label: 'Dismiss', color: 'white' }
            ]
          })
        }
      } else if (error.response.data) {
        this.$q.notify({
          color: 'negative',
          icon: 'fa fa-times-circle',
          position: 'top-right',
          message: error.response.data.message,
          timeout: 3000,
          actions: [
            { label: 'Dismiss', color: 'white' }
          ]
        })
      } else {
        this.$q.notify({
          color: 'negative',
          icon: 'fa fa-times-circle',
          position: 'top-right',
          message: 'Something went wrong',
          timeout: 3000,
          actions: [
            { label: 'Dismiss', color: 'white' }
          ]
        })
      }
    },
    noDataError (message) {
      this.$q.notify({
        color: 'warning',
        icon: 'fa fa-exclamation-triangle',
        position: 'top-right',
        message: message && message !== '' ? message : 'No data found!',
        timeout: 3000,
        actions: [
          { label: 'Dismiss', color: 'white' }
        ]
      })
    },
    errorFieldName (item, fieldName) {
      if (item.required && item.required.$invalid) return fieldName + ' is required'
      if (item.email && item.email.$invalid && !item.required.$invalid) return fieldName + ' is not valid email'
    }
  }
}
