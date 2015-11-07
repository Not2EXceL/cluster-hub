package org.theclustermc.hub.data.mutable

import org.theclustermc.hub.data.immutable.ImmutableDataValueImpl

sealed class PermissionData
(private val _identifier: Option[Symbol] = None, private val _has: Option[Boolean] = None)
    extends ImmutableDataValueImpl[Boolean, Symbol](_has, _identifier) with MutableDataValue[Boolean] {

    def permission = _identifier match {
        case Some(s) => s
        case _ => ""
    }

    /**
      * Default return is false if _permission exists
      * Default true if _permission is None or is ""
      *
      * @return has permission or not
      */
    def has = {

        if(_identifier.isEmpty || _identifier.get.equals(Symbol(""))) {
            true
        } else {
            _value match {
                case Some(bool) => bool
                case _ => false
            }
        }
    }


}

object PermissionData {
    import org.theclustermc.hub.utils.GenericOps.option

    def apply(identifier: Symbol, has: Boolean): PermissionData = apply(option(identifier), option(has))

    def apply(identifier: Option[Symbol], has: Option[Boolean]) = new PermissionData(identifier, has)
}
