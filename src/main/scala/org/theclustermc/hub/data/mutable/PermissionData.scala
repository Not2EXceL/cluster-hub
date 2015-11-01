package org.theclustermc.hub.data.mutable

import org.theclustermc.hub.data.immutable.ImmutableDataValueImpl

sealed class PermissionData
(private val _permission: Option[String] = None, private val _has: Option[Boolean] = None)
    extends ImmutableDataValueImpl[Boolean, String](_has, _permission) with MutableDataValue[Boolean] {

    def permission = _permission match {
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

        if(_permission.isEmpty || _permission.get.equals("")) {
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

    def apply[T >: String with Option[String], B >: Boolean with Option[Boolean]]
    (perm: T, has: B) = {
        var permOption: Option[String] = None
        var hasOption: Option[Boolean] = None
        perm match {
            case Some(p) => permOption = Option.apply(String.valueOf(p))
            case p => permOption = Option.apply(String.valueOf(p))
            case _ =>
        }
        has match {
            case Some(b) => hasOption = Option.apply(b.asInstanceOf[Boolean])
            case b => hasOption = Option.apply(b.asInstanceOf[Boolean])
            case _ =>
        }
        new PermissionData(permOption, hasOption)
    }
}
