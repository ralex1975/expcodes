# -*- coding: utf-8 -*-
__author__ = 'EXP (272629724@qq.com)'
__date__ = '2018-03-29 20:44'

import configparser

config = configparser.ConfigParser()
config.read(filenames='./../../../conf/url.ini', encoding='utf-8')



DEFAULT_CHARSET = 'utf-8'   # 默认编码

RSA_JS_PATH = './../res/MD5-RSA.js'     # 登陆密码加密算法的JS脚本
RSA_METHOD = 'getEncryption'            # RSA加密登陆密码的JS函数
GTK_JS_PATH = './../res/GTK.js'         # GTK生成算法的JS脚本
GTK_METHOD = 'getACSRFToken'            # 生成GTK码的JS函数

SIG_URL = config.get('lander', 'SIG_URL')               # 获取登陆用SIG的URL
VCODE_URL = config.get('lander', 'VCODE_URL')           # 获取登陆验证码的URL
VCODE_IMG_URL = config.get('lander', 'VCODE_IMG_URL')   # 获取登陆验证码图片的URL
XHR_LOGIN_URL = config.get('lander', 'XHR_LOGIN_URL')   # QQ空间登陆URL
QZONE_DOMAIN = config.get('lander', 'QZONE_DOMAIN')     # QQ空间域名地址(前缀)
ALBUM_LIST_URL = config.get('album', 'ALBUM_LIST_URL')  # 获取相册列表URL
PHOTO_LIST_URL = config.get('album', 'PHOTO_LIST_URL')  # 获取照片列表URL
MOOD_URL = config.get('mood', 'MOOD_URL')               # 获取说说分页内容URL
MOOD_REFERER = config.get('mood', 'MOOD_REFERER')       # 说说引用地址
MOOD_DOMAIN = config.get('mood', 'MOOD_DOMAIN')         # 说说域名地址


def QZONE_HOMR_URL(QQ):
    '''
    获取QQ空间首页地址
    :param QQ: QQ号
    :return:
    '''
    return '%(QZONE_DOMAIN)s%(QQ)s' % { 'QZONE_DOMAIN' : QZONE_DOMAIN, 'QQ' : QQ }


def ALBUM_URL(QQ, AID):
    '''
    获取QQ相册地址
    :param QQ: QQ号
    :param AID: 相册ID
    :return:
    '''
    return '%(QZONE_HOMR_URL)s/photo/%(AID)s' % { 'QZONE_HOMR_URL' : QZONE_HOMR_URL(QQ), 'AID' : AID }




