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

    def apply[T >: Symbol with Option[Symbol], B >: Boolean with Option[Boolean]]
    (identifier: T, has: B) = {
        var permOption: Option[Symbol] = None
        var hasOption: Option[Boolean] = None
        identifier match {
            case Some(p: Symbol) => permOption = Option(p)
            case p: Symbol => permOption = Option(p)
            case Some(p: String) => permOption = Option(Symbol(p))
            case p: String => permOption = Option(Symbol(p))
            case _ =>
        }
        has match {
            case Some(b) => hasOption = Option(b.asInstanceOf[Boolean])
            case b => hasOption = Option(b.asInstanceOf[Boolean])
            case _ =>
        }
        new PermissionData(permOption, hasOption)
    }
}