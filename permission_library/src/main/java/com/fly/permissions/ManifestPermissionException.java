package com.fly.permissions;


class ManifestPermissionException extends RuntimeException {

    ManifestPermissionException(String permission) {
        super(permission == null ?
                "No permissions are registered in the manifest file" :
                (permission + ": Permissions are not registered in the manifest file"));
    }
}