export const toastMessageMixins = {
  methods: {
    warningToast (message) {
      this.$q.notify({
        color: 'warning',
        icon: 'fa fa-exclamation-triangle',
        position: 'top-right',
        message,
        timeout: 3000,
        actions: [
          { label: 'Dismiss', color: 'white' }
        ]
      })
    },
    errorToast (message) {
      this.$q.notify({
        color: 'negative',
        icon: 'fa fa-times-circle',
        position: 'top-right',
        message,
        timeout: 3000,
        actions: [
          { label: 'Dismiss', color: 'white' }
        ]
      })
    },
    successToast (message) {
      this.$q.notify({
        color: 'green',
        icon: 'fa fa-check-circle',
        position: 'top-right',
        message,
        timeout: 3000,
        actions: [
          { label: 'Dismiss', color: 'white' }
        ]
      })
    }
  }
}
