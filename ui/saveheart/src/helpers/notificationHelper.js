
import { Notify } from 'quasar'

const showNotification = (options) => {
  const defaultOptions = {
    type: 'positive',
    position: 'top-right',
    timeout: 0,
    actions: [
      { label: 'Dismiss', color: 'white' }
    ]
  }

  Notify.create({ ...defaultOptions, ...options })
}

export {
  showNotification
}
